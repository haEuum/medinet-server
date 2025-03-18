package com.server.d2ackserver.domain.authentication.email.repository;

import com.server.d2ackserver.domain.authentication.email.domain.entity.AuthenticationNumber;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MailRepository extends JpaRepository<AuthenticationNumber, Long> {
    AuthenticationNumber findByEmail(String email);
    void deleteByEmail(String email);
}
