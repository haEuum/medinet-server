package com.server.d2ackserver.domain.oauth.dto.response;

import com.fasterxml.jackson.annotation.JsonProperty;

public record KakaoUserInfo(
        @JsonProperty("id") String id,
        @JsonProperty("kakao_account") KakaoAccount kakaoAccount
) implements OAuthUserInfo {

    @Override
    public String id() {
        return id;
    }

    @Override
    public String email() {
        return kakaoAccount.email();
    }

    @Override
    public String name() {
        return kakaoAccount.profile().nickname();
    }

    public record KakaoAccount(
            @JsonProperty("email") String email,
            @JsonProperty("profile") KakaoProfile profile
    ) {}

    public record KakaoProfile(
            @JsonProperty("nickname") String nickname
    ) {}
}

