package com.server.d2ackserver.global.security.jwt.dto;

public record Jwt(
        String accessToken,
        String refreshToken
) {
}
