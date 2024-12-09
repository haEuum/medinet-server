package com.server.d2ackserver.domain.auth.dto.request;

public record SignUpReqeust(
        String name,
        String email,
        String password
) {
}
