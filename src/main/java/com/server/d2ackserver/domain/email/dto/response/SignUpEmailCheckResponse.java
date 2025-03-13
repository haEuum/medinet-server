package com.server.d2ackserver.domain.email.dto.response;

public record SignUpEmailCheckResponse(
        Boolean isMatch
) {
    public static SignUpEmailCheckResponse of(Boolean isMatch) {
        return new SignUpEmailCheckResponse(isMatch);
    }
}
