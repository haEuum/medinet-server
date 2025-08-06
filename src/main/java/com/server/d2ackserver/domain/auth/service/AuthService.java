package com.server.d2ackserver.domain.auth.service;

import com.server.d2ackserver.domain.auth.dto.request.HospitalSignupRequest;
import com.server.d2ackserver.domain.auth.dto.request.LoginRequest;
import com.server.d2ackserver.domain.auth.dto.request.ReissueRequest;
import com.server.d2ackserver.domain.auth.dto.request.SignUpReqeust;
import com.server.d2ackserver.domain.auth.dto.response.HospitalSignupResponse;
import com.server.d2ackserver.domain.auth.dto.response.SignUpResponse;
import com.server.d2ackserver.global.security.jwt.dto.Jwt;

public interface AuthService {
    SignUpResponse signUp(SignUpReqeust reqeust);
    HospitalSignupResponse hospitalSignUp(HospitalSignupRequest reqeust);

    Jwt login(LoginRequest request);
    Jwt reissue(ReissueRequest request);
}
