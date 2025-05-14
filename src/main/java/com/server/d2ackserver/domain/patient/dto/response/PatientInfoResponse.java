package com.server.d2ackserver.domain.patient.dto.response;

import com.server.d2ackserver.domain.patient.domain.entity.PatientEntity;

import java.time.LocalDateTime;

public record PatientInfoResponse(
        Long id,
        String name,
        Boolean agree,
        LocalDateTime createdAt,
        LocalDateTime updatedAt
) {
    public static PatientInfoResponse of(PatientEntity request) {
        return new PatientInfoResponse(request.getId(), request.getName(), request.getAgree(), request.getCreatedAt(), request.getUpdatedAt());
    }
}
