package com.server.d2ackserver.domain.auth.dto.response;

import com.server.d2ackserver.domain.user.domain.enitty.UserEntity;
import com.server.d2ackserver.domain.user.domain.enums.UserRole;

import java.time.LocalDateTime;

public record SignUpResponse(
        Long id,
        String name,
        String email,
        String password,
        UserRole role,
        LocalDateTime createdAt,
        LocalDateTime updatedAt
) {
    public static SignUpResponse of(UserEntity user) {
        return new SignUpResponse(user.getId(), user.getName(), user.getEmail(), user.getPassword(), user.getRole(), user.getCreatedAt(), user.getUpdatedAt());
    }
}
