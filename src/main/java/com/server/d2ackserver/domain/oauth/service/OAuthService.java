package com.server.d2ackserver.domain.oauth.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.server.d2ackserver.domain.auth.exception.AuthError;
import com.server.d2ackserver.domain.auth.repository.RefreshTokenRepository;
import com.server.d2ackserver.domain.oauth.dto.request.OAuthAdditionalInfo;
import com.server.d2ackserver.domain.oauth.dto.response.OAuthUserInfo;
import com.server.d2ackserver.domain.oauth.dto.response.GoogleUserInfo;
import com.server.d2ackserver.domain.oauth.dto.response.KakaoUserInfo;
import com.server.d2ackserver.domain.oauth.dto.response.OAuthTokenResponse;
import com.server.d2ackserver.domain.oauth.exception.OAuthError;
import com.server.d2ackserver.domain.oauth.property.OAuthProperties;
import com.server.d2ackserver.domain.user.domain.enitty.UserEntity;
import com.server.d2ackserver.domain.user.domain.enums.UserProvider;
import com.server.d2ackserver.domain.user.domain.enums.UserRole;
import com.server.d2ackserver.domain.user.repository.UserRepository;
import com.server.d2ackserver.global.exception.CustomException;
import com.server.d2ackserver.global.security.jwt.dto.Jwt;
import com.server.d2ackserver.global.security.jwt.provider.JwtProvider;
import lombok.RequiredArgsConstructor;
import org.springframework.http.*;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

@Service
@RequiredArgsConstructor
public class OAuthService {

    private final RestTemplate restTemplate;
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtProvider jwtProvider;
    private final RefreshTokenRepository refreshTokenRepository;
    private final OAuthProperties oAuthProperties;  // 추가

    public Jwt oAuthLogin(String provider, String code) {
        OAuthTokenResponse tokenResponse = fetchToken(provider, code);
        OAuthUserInfo oauthUserInfo = fetchUserInfo(provider, tokenResponse.accessToken());

        UserProvider userProvider = provider.equals("google") ? UserProvider.GOOGLE : UserProvider.KAKAO;

        UserEntity user = userRepository.findByEmail(oauthUserInfo.email());

        if (user == null) {
            OAuthAdditionalInfo addInfo = restTemplate.getForObject("http://localhost:8080/oauth2/additional-info", OAuthAdditionalInfo.class);

            user = UserEntity.builder()
                    .name(oauthUserInfo.name())
                    .password(passwordEncoder.encode("default_password"))
                    .email(oauthUserInfo.email())
                    .provider(userProvider)
                    .field(addInfo.field())
                    .userClass(addInfo.userClass())
                    .role(UserRole.User)
                    .build();

            userRepository.save(user);
        }

        UserEntity loginUser = userRepository.findById(user.getId())
                .orElseThrow(() -> new CustomException(AuthError.USER_NOT_FOUND));

        Jwt token = jwtProvider.generateToken(loginUser);
        refreshTokenRepository.save(loginUser.getEmail(), token.refreshToken());

        return token;
    }

    private OAuthTokenResponse fetchToken(String provider, String code) {
        OAuthProperties.ProviderProperties providerProps = oAuthProperties.getProviders().get(provider);
        if (providerProps == null) {
            throw new CustomException(OAuthError.UNSUPPORTED_PROVIDER);
        }

        String tokenUrl;
        MultiValueMap<String, String> params = new LinkedMultiValueMap<>();

        if (provider.equals("google")) {
            tokenUrl = "https://oauth2.googleapis.com/token";
        } else if (provider.equals("kakao")) {
            tokenUrl = "https://kauth.kakao.com/oauth/token";
        } else {
            throw new CustomException(OAuthError.UNSUPPORTED_PROVIDER);
        }

        params.add("client_id", providerProps.getClientId());
        params.add("client_secret", providerProps.getClientSecret());
        params.add("redirect_uri", providerProps.getRedirectUri());
        params.add("code", code);
        params.add("grant_type", "authorization_code");

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);

        HttpEntity<MultiValueMap<String, String>> request = new HttpEntity<>(params, headers);
        ResponseEntity<OAuthTokenResponse> response = restTemplate.postForEntity(tokenUrl, request, OAuthTokenResponse.class);

        if (!response.getStatusCode().is2xxSuccessful()) {
            throw new CustomException(OAuthError.OAUTH_TOKEN_ERROR);
        }

        return response.getBody();
    }
    private OAuthUserInfo fetchUserInfo(String provider, String accessToken) {
        String userInfoUrl;
        if (provider.equals("google")) {
            userInfoUrl = "https://www.googleapis.com/oauth2/v3/userinfo";
        } else if (provider.equals("kakao")) {
            userInfoUrl = "https://kapi.kakao.com/v2/user/me";
        } else {
            throw new CustomException(OAuthError.UNSUPPORTED_PROVIDER);
        }

        HttpHeaders headers = new HttpHeaders();
        headers.setBearerAuth(accessToken);

        HttpEntity<Void> request = new HttpEntity<>(headers);
        ResponseEntity<String> response = restTemplate.exchange(userInfoUrl, HttpMethod.GET, request, String.class);

        if (!response.getStatusCode().is2xxSuccessful()) {
            throw new CustomException(OAuthError.OAUTH_USER_INFO_ERROR);
        }

        return parseUserInfo(provider, response.getBody());
    }
    private OAuthUserInfo parseUserInfo(String provider, String responseBody) {
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            if (provider.equals("google")) {
                return objectMapper.readValue(responseBody, GoogleUserInfo.class);
            } else if (provider.equals("kakao")) {
                return objectMapper.readValue(responseBody, KakaoUserInfo.class);
            }
        } catch (JsonProcessingException e) {
            throw new CustomException(OAuthError.OAUTH_USER_INFO_PARSE_ERROR);
        }
        throw new CustomException(OAuthError.UNSUPPORTED_PROVIDER);
    }


}
