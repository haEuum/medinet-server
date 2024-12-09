package com.server.d2ackserver.domain.auth.repository;

import java.util.Optional;

public interface RefreshTokenRepository {
    void save(String email, String refreshToken);  // email -> userId

    Optional<String> findByEmail(String email);  // email -> userId

    void deleteByEmail(String email);  // email -> userId

    boolean existsByEmail(String email);  // email -> userId
}

