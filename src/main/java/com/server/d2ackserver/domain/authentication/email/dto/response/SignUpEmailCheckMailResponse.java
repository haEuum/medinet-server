package com.server.d2ackserver.domain.authentication.email.dto.response;


import com.server.d2ackserver.domain.authentication.email.domain.entity.AuthenticationNumber;

public record SignUpEmailCheckMailResponse(
        String email,
        Long number
) {
    public static SignUpEmailCheckMailResponse of(AuthenticationNumber authNum) {
        return new SignUpEmailCheckMailResponse(authNum.getEmail(), authNum.getNumber());
    }
}
