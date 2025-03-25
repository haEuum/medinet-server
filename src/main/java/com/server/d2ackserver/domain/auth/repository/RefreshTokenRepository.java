package com.server.d2ackserver.domain.auth.repository;

import java.util.Optional;

public interface RefreshTokenRepository {
    void save(String phoneNumber, String refreshToken);

    Optional<String> findByPhoneNumber(String phoneNumber);

    void deleteByPhoneNumber(String phoneNumber);

    boolean existsByPhoneNumber(String phoneNumber);
}

