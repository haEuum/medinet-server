package com.server.d2ackserver.domain.user.service;

import com.server.d2ackserver.domain.user.dto.response.UserInfoResponse;

public interface UserService {
    UserInfoResponse getMyInfo();
}
