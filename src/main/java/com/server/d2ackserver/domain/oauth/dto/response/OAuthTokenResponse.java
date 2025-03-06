package com.server.d2ackserver.domain.oauth.dto.response;

import com.fasterxml.jackson.annotation.JsonProperty;

public record OAuthTokenResponse(
        @JsonProperty("access_token")
        String accessToken,
        @JsonProperty("expires_in")
        Long expiresIn,
        @JsonProperty("refresh_token")
        String refreshToken,
        @JsonProperty("scope")
        String scope,
        @JsonProperty("token_type")
        String tokenType
) {
}
