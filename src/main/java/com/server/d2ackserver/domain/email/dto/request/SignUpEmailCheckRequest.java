package com.server.d2ackserver.domain.email.dto.request;

public record SignUpEmailCheckRequest(
        String email,
        Long enterNum
) {
}
