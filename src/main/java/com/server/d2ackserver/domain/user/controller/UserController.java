package com.server.d2ackserver.domain.user.controller;

import com.server.d2ackserver.domain.user.dto.response.UserInfoResponse;
import com.server.d2ackserver.domain.user.service.UserService;
import com.server.d2ackserver.global.response.BaseResponse;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;


    @Operation(summary = "내 정보 조회", description = "")
    @GetMapping("/info/my")
    public ResponseEntity<BaseResponse<UserInfoResponse>> getMyInfo() {
        return BaseResponse.of(userService.getMyInfo(), 200, "내 정보 불러오기 성공");
    }
}
