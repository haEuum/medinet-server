package com.server.d2ackserver.domain.user.dto.response;

import com.server.d2ackserver.domain.user.domain.enitty.UserEntity;
import com.server.d2ackserver.domain.user.domain.enums.UserClass;
import com.server.d2ackserver.domain.user.domain.enums.UserField;
import com.server.d2ackserver.domain.user.domain.enums.UserProvider;
import com.server.d2ackserver.domain.user.domain.enums.UserRole;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.time.LocalDateTime;

public record UserInfoResponse(
        Long id,
        String name,
        UserProvider provider,
        UserField field,
        UserClass userClass,
        UserRole role,
        LocalDateTime createdAt,
        LocalDateTime updatedAt
) {
    public static UserInfoResponse of(UserEntity user) {
        return new UserInfoResponse(user.getId(), user.getName(), user.getProvider(), user.getField(), user.getUserClass(), user.getRole(), user.getCreatedAt(), user.getUpdatedAt());
    }
}
