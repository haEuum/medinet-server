package com.server.d2ackserver.domain.oauth.controller;

import com.server.d2ackserver.domain.oauth.dto.request.OAuthAdditionalInfo;
import com.server.d2ackserver.domain.oauth.service.OAuthService;
import com.server.d2ackserver.global.response.BaseResponse;
import com.server.d2ackserver.global.security.jwt.dto.Jwt;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/oauth2")
@RequiredArgsConstructor
public class OAuthController {

    private final OAuthService oauthService;

    @GetMapping("/callback/{provider}")
    public ResponseEntity<BaseResponse<Jwt>> oauthLogin(
            @PathVariable String provider,
            @RequestParam("code") String code) {

        return BaseResponse.of(oauthService.oAuthLogin(provider, code), 200, "로그인 성공");
    }


    @PostMapping("/additional-info")
    public ResponseEntity<BaseResponse<Void>> postAdditionalInfo(@RequestBody OAuthAdditionalInfo request) {
        return BaseResponse.of(null, 200, "회원가입 정보 저장 완료");
    }
}