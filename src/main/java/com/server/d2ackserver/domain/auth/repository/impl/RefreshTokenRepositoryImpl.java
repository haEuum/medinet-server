package com.server.d2ackserver.domain.auth.repository.impl;


import com.server.d2ackserver.domain.auth.repository.RefreshTokenRepository;
import com.server.d2ackserver.global.security.jwt.config.JwtProperties;
import lombok.RequiredArgsConstructor;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.concurrent.TimeUnit;

@Repository
@RequiredArgsConstructor
public class RefreshTokenRepositoryImpl implements RefreshTokenRepository {
    private final JwtProperties jwtProperties;
    private final RedisTemplate<String, String> redisTemplate;

    @Override
    public void save(String phoneNumber, String refreshToken) {
        redisTemplate.opsForValue().set("refreshToken:" + phoneNumber, refreshToken, jwtProperties.getRefreshTokenExpiration(), TimeUnit.MILLISECONDS);
    }


    @Override
    public Optional<String> findByPhoneNumber(String phoneNumber) {
        return Optional.ofNullable(redisTemplate.opsForValue().get("refreshToken:" + phoneNumber));
    }

    @Override
    public void deleteByPhoneNumber(String phoneNumber) {
        redisTemplate.delete("refreshToken:" + phoneNumber);
    }

    @Override
    public boolean existsByPhoneNumber(String phoneNumber) {
        return Boolean.TRUE.equals(redisTemplate.hasKey("refreshToken:" + phoneNumber));
    }
}
