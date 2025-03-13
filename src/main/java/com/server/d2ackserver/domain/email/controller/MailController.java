package com.server.d2ackserver.domain.email.controller;


import com.server.d2ackserver.domain.email.dto.request.MailSendRequest;
import com.server.d2ackserver.domain.email.dto.request.SignUpEmailCheckRequest;
import com.server.d2ackserver.domain.email.dto.response.SignUpEmailCheckMailResponse;
import com.server.d2ackserver.domain.email.dto.response.SignUpEmailCheckResponse;
import com.server.d2ackserver.domain.email.service.MailService;
import com.server.d2ackserver.global.response.BaseResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@Tag(name = "Mail API")
public class MailController {
    private final MailService mailService;
    private int number;

    // 인증 이메일 전송
    @PostMapping("/mailSend")
    @Operation(summary = "이메일 인증", description = "**request**\n\nemail : 인증번호를 전송할 이메일\n\n**response**\n\nemail : 인증번호를 전송한 이메일\n\nnumber : 전송한 인증번호")
    public ResponseEntity<BaseResponse<SignUpEmailCheckMailResponse>> mailSend(@RequestBody MailSendRequest request) {
        return BaseResponse.of(mailService.sendSignUpEmailCheckMail(request.email()), 200, "인증이메일 전송 완료");
    }

//    @PostMapping("/mailCheck")
//    public ResponseEntity<BaseResponse<SignUpEmailCheckResponse>> mailCheck(@RequestBody SignUpEmailCheckRequest request) {
//        return BaseResponse.of(mailService.sendSignUpEmailCheck(request), 200, "이메일 확인 완료") ;
//    }
}