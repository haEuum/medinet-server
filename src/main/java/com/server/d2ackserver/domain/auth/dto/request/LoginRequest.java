package com.server.d2ackserver.domain.auth.dto.request;

public record LoginRequest(
        String phoneNumber,
        String password
) {
}
