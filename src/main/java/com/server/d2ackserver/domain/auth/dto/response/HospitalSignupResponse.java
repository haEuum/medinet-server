package com.server.d2ackserver.domain.auth.dto.response;

import com.server.d2ackserver.domain.hospital.domain.entity.HospitalEntity;

public record HospitalSignupResponse(
        String name,
        String address,
        String telephone
) {
    public static HospitalSignupResponse of(HospitalEntity request) {
        return new HospitalSignupResponse(request.getName(), request.getAddress(), request.getTelephone());
    }
}
