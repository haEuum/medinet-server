package com.server.d2ackserver.domain.authentication.sms.controller;


import com.server.d2ackserver.domain.authentication.sms.dto.request.SmsRequest;
import com.server.d2ackserver.domain.authentication.sms.service.SmsService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
@RequestMapping("/sms")
public class SmsController {

    private final SmsService smsService;

    @PostMapping("/send")
    @Operation(summary = "전화번호 인증", description = "phoneNum : 수신자 번호")
    public ResponseEntity<?> SendSMS(@Valid @RequestBody SmsRequest smsRequestDto){
        System.out.println(smsRequestDto.phoneNum());
        smsService.SendSms(smsRequestDto);
        return ResponseEntity.ok("sms sended.");
    }
}
