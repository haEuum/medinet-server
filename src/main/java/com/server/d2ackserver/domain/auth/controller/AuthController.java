package com.server.d2ackserver.domain.auth.controller;

import com.server.d2ackserver.domain.auth.service.AuthService;
import com.server.d2ackserver.global.response.BaseResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class AuthController {
    private final AuthService authService;

    @Tag(name = "로그인 Auth", description = "로그인 API")
    @Operation(summary = "로그인", description = "토큰 발급")
    @PostMapping("/login")
    public ResponseEntity<BaseResponse<com.server.pin.global.security.jwt.dto.Jwt>> login(@RequestBody LoginRequest request) {
        return BaseResponse.of(authService.login(request), 200, "로그인 성공");
    }

    @Tag(name = "로그인 Auth", description = "로그인 API")
    @Operation(summary = "리이슈", description = "토큰 재발급")
    @PostMapping("/reissue")
    public ResponseEntity<BaseResponse<com.server.pin.global.security.jwt.dto.Jwt>> reissue(@RequestBody ReissueRequest request) {
        return BaseResponse.of(authService.reissue(request), 200, "토큰 재발급 성공");
    }
}
