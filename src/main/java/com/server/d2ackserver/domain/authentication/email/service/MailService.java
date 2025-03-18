package com.server.d2ackserver.domain.authentication.email.service;


import com.server.d2ackserver.domain.authentication.email.dto.request.SignUpEmailCheckRequest;
import com.server.d2ackserver.domain.authentication.email.dto.response.SignUpEmailCheckMailResponse;
import com.server.d2ackserver.domain.authentication.email.dto.response.SignUpEmailCheckResponse;

public interface MailService {
    SignUpEmailCheckMailResponse sendSignUpEmailCheckMail(String mail);
    SignUpEmailCheckResponse sendSignUpEmailCheck(SignUpEmailCheckRequest request);
}
