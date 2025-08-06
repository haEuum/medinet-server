package com.server.d2ackserver.domain.auth.service.impl;

import com.server.d2ackserver.domain.auth.dto.request.HospitalSignupRequest;
import com.server.d2ackserver.domain.auth.dto.request.LoginRequest;
import com.server.d2ackserver.domain.auth.dto.request.ReissueRequest;
import com.server.d2ackserver.domain.auth.dto.request.SignUpReqeust;
import com.server.d2ackserver.domain.auth.dto.response.HospitalSignupResponse;
import com.server.d2ackserver.domain.auth.dto.response.SignUpResponse;
import com.server.d2ackserver.domain.auth.exception.AuthError;
import com.server.d2ackserver.domain.auth.repository.RefreshTokenRepository;
import com.server.d2ackserver.domain.auth.service.AuthService;
import com.server.d2ackserver.domain.hospital.domain.entity.HospitalEntity;
import com.server.d2ackserver.domain.hospital.domain.repository.HospitalRepository;
import com.server.d2ackserver.domain.user.domain.enitty.UserEntity;
import com.server.d2ackserver.domain.user.domain.enums.UserProvider;
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
    private final HospitalRepository hospitalRepository;

    @Override
    public SignUpResponse signUp(SignUpReqeust reqeust) {
        UserEntity user = UserEntity.builder()
                .name(reqeust.name())
                .password(encoder.encode(reqeust.password()))
                .role(UserRole.ROLE_USER)
                .provider(UserProvider.MEDINET)
                .phoneNumber(reqeust.phoneNumber())
                .biometricAuthNum(reqeust.biometricAuthNum())
                .field(reqeust.field())
                .userClass(reqeust.userClass())
                .build();


        return SignUpResponse.of(userRepository.save(user));
    }

    @Override
    public HospitalSignupResponse hospitalSignUp(HospitalSignupRequest reqeust) {
        HospitalEntity hospital = HospitalEntity.builder()
                .name(reqeust.name())
                .password(encoder.encode(reqeust.password()))
                .address(reqeust.address())
                .phoneNumber(reqeust.phoneNumber())
                .build();

        return HospitalSignupResponse.of(hospitalRepository.save(hospital));
    }

    @Override
    public Jwt login(LoginRequest request) {
        UserEntity usertemp = userRepository.findByPhoneNumber(request.phoneNumber());
        if (usertemp == null) {
            throw new CustomException(AuthError.USER_NOT_FOUND);
        }
        UserEntity user = userRepository.findById(usertemp.getId())
                .orElseThrow(() -> new CustomException(AuthError.USER_NOT_FOUND));

        if (!encoder.matches(request.password(), user.getPassword())) {
            throw new CustomException(AuthError.PASSWORD_WRONG);
        }

        Jwt token = jwtProvider.generateToken(user);

        refreshTokenRepository.save(user.getPhoneNumber(), token.refreshToken());

        return token;
    }

    @Override
    public Jwt hospitalLogin(LoginRequest request) {
        HospitalEntity hospitaltemp = hospitalRepository.findByPhoneNumber(request.phoneNumber());
        if (hospitaltemp == null) {
            throw new CustomException(AuthError.USER_NOT_FOUND);
        }
        HospitalEntity hospital = hospitalRepository.findById(hospitaltemp.getId())
                .orElseThrow(() -> new CustomException(AuthError.USER_NOT_FOUND));

        if (!encoder.matches(request.password(), hospital.getPassword())) {
            throw new CustomException(AuthError.PASSWORD_WRONG);
        }

        Jwt token = jwtProvider.generateHospitalToken(hospital);

        refreshTokenRepository.save(hospital.getPhoneNumber(), token.refreshToken());

        return token;
    }

    @Override
    public Jwt reissue(ReissueRequest request) {
        if (jwtProvider.getType(request.refreshToken()) != JwtType.REFRESH)
            throw new CustomException(JwtError.INVALID_TOKEN);

        String phoneNumber = jwtProvider.getUserId(request.refreshToken());

        if (!refreshTokenRepository.existsByPhoneNumber(phoneNumber))
            throw new CustomException(JwtError.INVALID_TOKEN);

        String refreshToken = refreshTokenRepository.findByPhoneNumber(phoneNumber)
                .orElseThrow(() -> new CustomException(JwtError.INVALID_TOKEN));

        if (!refreshToken.equals(request.refreshToken()))
            throw new CustomException(JwtError.INVALID_TOKEN);

        UserEntity user = userRepository.findById(userRepository.findByPhoneNumber(phoneNumber).getId())
                .orElseThrow(() -> new CustomException(AuthError.USER_NOT_FOUND));

        Jwt token = jwtProvider.generateToken(user);

        refreshTokenRepository.save(phoneNumber, token.refreshToken());

        return token;
    }
}
