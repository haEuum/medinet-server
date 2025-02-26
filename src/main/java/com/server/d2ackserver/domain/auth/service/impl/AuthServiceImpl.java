package com.server.d2ackserver.domain.auth.service.impl;

import com.server.d2ackserver.domain.auth.dto.request.LoginRequest;
import com.server.d2ackserver.domain.auth.dto.request.ReissueRequest;
import com.server.d2ackserver.domain.auth.dto.request.SignUpReqeust;
import com.server.d2ackserver.domain.auth.dto.response.SignUpResponse;
import com.server.d2ackserver.domain.auth.exception.AuthError;
import com.server.d2ackserver.domain.auth.repository.RefreshTokenRepository;
import com.server.d2ackserver.domain.auth.service.AuthService;
import com.server.d2ackserver.domain.user.domain.enitty.UserEntity;
import com.server.d2ackserver.domain.user.domain.enums.UserRole;
import com.server.d2ackserver.domain.user.repository.UserRepository;
import com.server.d2ackserver.global.exception.CustomException;
import com.server.d2ackserver.global.security.jwt.dto.Jwt;
import com.server.d2ackserver.global.security.jwt.enums.JwtType;
import com.server.d2ackserver.global.security.jwt.error.JwtError;
import com.server.d2ackserver.global.security.jwt.provider.JwtProvider;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {
    private final UserRepository userRepository;
    private final RefreshTokenRepository refreshTokenRepository;
    private final PasswordEncoder encoder;
    private final JwtProvider jwtProvider;

    @Override
    public SignUpResponse signUp(SignUpReqeust reqeust) {
        UserEntity user = UserEntity.builder()
                .name(reqeust.name())
                .email(reqeust.email())
                .password(encoder.encode(reqeust.password()))
                .role(UserRole.Authenticated)
                .build();


        return SignUpResponse.of(userRepository.save(user));
    }

    @Override
    public Jwt login(LoginRequest request) {
        UserEntity usertemp = userRepository.findByEmail(request.email());
        if (usertemp == null) {
            throw new CustomException(AuthError.USER_NOT_FOUND);
        }
        UserEntity user = userRepository.findById(usertemp.getId())
                .orElseThrow(() -> new CustomException(AuthError.USER_NOT_FOUND));

        if (!encoder.matches(request.password(), user.getPassword())) {
            throw new CustomException(AuthError.PASSWORD_WRONG);
        }

        Jwt token = jwtProvider.generateToken(user);

        refreshTokenRepository.save(user.getEmail(), token.refreshToken());

        return token;
    }

    @Override
    public Jwt reissue(ReissueRequest request) {
        if (jwtProvider.getType(request.refreshToken()) != JwtType.REFRESH)
            throw new CustomException(JwtError.INVALID_TOKEN);

        String email = jwtProvider.getUserId(request.refreshToken());

        if (!refreshTokenRepository.existsByEmail(email))
            throw new CustomException(JwtError.INVALID_TOKEN);

        String refreshToken = refreshTokenRepository.findByEmail(email)
                .orElseThrow(() -> new CustomException(JwtError.INVALID_TOKEN));

        if (!refreshToken.equals(request.refreshToken()))
            throw new CustomException(JwtError.INVALID_TOKEN);

        UserEntity user = userRepository.findById(userRepository.findByEmail(email).getId())
                .orElseThrow(() -> new CustomException(AuthError.USER_NOT_FOUND));

        Jwt token = jwtProvider.generateToken(user);

        refreshTokenRepository.save(email, token.refreshToken());

        return token;
    }
}
