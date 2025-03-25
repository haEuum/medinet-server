package com.server.d2ackserver.domain.user.repository;

import com.server.d2ackserver.domain.user.domain.enitty.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<UserEntity, Long> {
    UserEntity findByPhoneNumber(String phoneNumber);
}
