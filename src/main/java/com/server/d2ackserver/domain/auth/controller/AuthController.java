package com.server.d2ackserver.domain.auth.controller;


import com.server.d2ackserver.domain.auth.dto.request.LoginRequest;
import com.server.d2ackserver.domain.auth.dto.request.ReissueRequest;
import com.server.d2ackserver.domain.auth.dto.request.SignUpReqeust;
import com.server.d2ackserver.domain.auth.dto.response.SignUpResponse;
import com.server.d2ackserver.domain.auth.service.AuthService;
import com.server.d2ackserver.domain.auth.dto.request.HospitalSignupRequest;
import com.server.d2ackserver.domain.auth.dto.response.HospitalSignupResponse;
import com.server.d2ackserver.domain.hospital.service.HospitalService;
import com.server.d2ackserver.global.response.BaseResponse;
import com.server.d2ackserver.global.security.jwt.dto.Jwt;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@Tag(name = "Auth API")
public class AuthController {
    private final AuthService authService;

    @Operation(summary = "회원가입", description = "**유저 생성**\n\nname : 이름\n\npassword : 비밀번호\n\nphoneNumber : 전화번호\n\nbiometricAuthNum : 생체인증 고유번호\n\nfield : 직군\n\nuserClass : 분야\n\n직군, 분야 변수명: https://www.notion.so/1a202833a9c3809b9e2ff8a3cfc13fcd?pvs=4")
    @PostMapping("/signup")
    public ResponseEntity<BaseResponse<SignUpResponse>> signUp(@RequestBody SignUpReqeust request) {
        return BaseResponse.of(authService.signUp(request), 200, "회원가입 성공");
    }

    @Operation(summary = "병원 회원가입", description = "**병원 유저 생성**\n\nname : 병원명\n\npassword : 비밀번호\n\naddress : 주소\n\nphoneNumber : 전화번호")
    @PostMapping("/signup/hospital")
    public ResponseEntity<BaseResponse<HospitalSignupResponse>> hospitalSignUp(@RequestBody HospitalSignupRequest request) {
        return BaseResponse.of(authService.hospitalSignUp(request), 200, "회원가입 성공");
    }

    @Operation(summary = "로그인", description = "**토큰 발급**\n\nphoneNumber : 전화번호\n\npassword : 비밀번호")
    @PostMapping("/login")
    public ResponseEntity<BaseResponse<Jwt>> login(@RequestBody LoginRequest request) {
        return BaseResponse.of(authService.login(request), 200, "로그인 성공");
    }

    @Operation(summary = "병원 로그인", description = "**토큰 발급**\n\nphoneNumber : 전화번호\n\npassword : 비밀번호")
    @PostMapping("/login/hospital")
    public ResponseEntity<BaseResponse<Jwt>> hospitalLogin(@RequestBody LoginRequest request) {
        return BaseResponse.of(authService.hospitalLogin(request), 200, "로그인 성공");
    }

    @Operation(summary = "리이슈", description = "**토큰 재발급**\n\nrefreshToken : 리프레쉬 토큰")
    @PostMapping("/reissue")
    public ResponseEntity<BaseResponse<Jwt>> reissue(@RequestBody ReissueRequest request) {
        return BaseResponse.of(authService.reissue(request), 200, "토큰 재발급 성공");
    }
}

