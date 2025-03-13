package com.server.d2ackserver.domain.auth.dto.response;

import com.server.d2ackserver.domain.user.domain.enitty.UserEntity;
import com.server.d2ackserver.domain.user.domain.enums.UserClass;
import com.server.d2ackserver.domain.user.domain.enums.UserField;
import com.server.d2ackserver.domain.user.domain.enums.UserProvider;
import com.server.d2ackserver.domain.user.domain.enums.UserRole;

import java.time.LocalDateTime;

public record SignUpResponse(
        Long id,
        String name,
        String password,
        String email,
        UserProvider provider,
        String phoneNumber,
        Long biometricAuthNum ,
        UserField field,
        UserClass userClass,
        UserRole role,
        LocalDateTime createdAt,
        LocalDateTime updatedAt
) {
    public static SignUpResponse of(UserEntity user) {
        return new SignUpResponse(user.getId(), user.getName(), user.getEmail(), user.getPassword(), user.getProvider(), user.getPhoneNumber(), user.getBiometricAuthNum() , user.getField(), user.getUserClass(), user.getRole(), user.getCreatedAt(), user.getUpdatedAt());
    }
}
