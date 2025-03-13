package com.server.d2ackserver.domain.email.service;


import com.server.d2ackserver.domain.email.dto.request.SignUpEmailCheckRequest;
import com.server.d2ackserver.domain.email.dto.response.SignUpEmailCheckMailResponse;
import com.server.d2ackserver.domain.email.dto.response.SignUpEmailCheckResponse;

public interface MailService {
    SignUpEmailCheckMailResponse sendSignUpEmailCheckMail(String mail);
    SignUpEmailCheckResponse sendSignUpEmailCheck(SignUpEmailCheckRequest request);
}
