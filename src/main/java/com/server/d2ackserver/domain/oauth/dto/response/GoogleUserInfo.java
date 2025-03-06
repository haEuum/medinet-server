package com.server.d2ackserver.domain.oauth.dto.response;

import com.fasterxml.jackson.annotation.JsonProperty;

public record GoogleUserInfo(
        @JsonProperty("sub") String id,
        @JsonProperty("email") String email,
        @JsonProperty("name") String name
) implements OAuthUserInfo {}

