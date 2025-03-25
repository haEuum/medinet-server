package com.server.d2ackserver.domain.user.service.impl;

import com.server.d2ackserver.domain.user.domain.enitty.UserEntity;
import com.server.d2ackserver.domain.user.dto.response.UserInfoResponse;
import com.server.d2ackserver.domain.user.repository.UserRepository;
import com.server.d2ackserver.domain.user.service.UserService;
import com.server.d2ackserver.global.security.holder.SecurityHolder;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final SecurityHolder securityHolder;

    @Override
    public UserInfoResponse getMyInfo() {

        UserEntity user = userRepository.findByPhoneNumber(securityHolder.getPrincipal().getPhoneNumber());
        return UserInfoResponse.of(user);
    }
}
