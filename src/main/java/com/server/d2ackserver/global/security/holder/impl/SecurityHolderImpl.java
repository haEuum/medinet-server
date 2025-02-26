package com.server.d2ackserver.global.security.holder.impl;


import com.server.d2ackserver.domain.auth.exception.AuthError;
import com.server.d2ackserver.domain.user.domain.enitty.UserEntity;
import com.server.d2ackserver.domain.user.repository.UserRepository;
import com.server.d2ackserver.global.exception.CustomException;
import com.server.d2ackserver.global.security.holder.SecurityHolder;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
@RequiredArgsConstructor
public class SecurityHolderImpl implements SecurityHolder {
    private final UserRepository userRepository;

    @Override
    public UserEntity getPrincipal() {
        String email = SecurityContextHolder.getContext().getAuthentication().getName();

        return Optional.of( userRepository.findByEmail(email) ).orElseThrow(() -> new CustomException(AuthError.USER_NOT_FOUND));
    }
}
