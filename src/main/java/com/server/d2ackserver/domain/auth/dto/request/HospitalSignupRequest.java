package com.server.d2ackserver.domain.auth.dto.request;

public record HospitalSignupRequest(
        String name,
        String password,
        String address,
        String phoneNumber
) {
}
