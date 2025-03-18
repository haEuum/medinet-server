package com.server.d2ackserver.domain.authentication.sms.dto.response;

public record SmsResponse(
        String phoneNum,
        String authenticationCode
) {
    public static SmsResponse of(String phoneNum, String authenticationCode) {
        return new SmsResponse(phoneNum, authenticationCode);
    }
}
