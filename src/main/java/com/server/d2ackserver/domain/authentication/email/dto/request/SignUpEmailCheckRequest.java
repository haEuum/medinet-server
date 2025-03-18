package com.server.d2ackserver.domain.authentication.email.dto.request;

public record SignUpEmailCheckRequest(
        String email,
        Long enterNum
) {
}
