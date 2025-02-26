package com.server.d2ackserver.global.security.holder;


import com.server.d2ackserver.domain.user.domain.enitty.UserEntity;

public interface SecurityHolder {
    UserEntity getPrincipal();
}
