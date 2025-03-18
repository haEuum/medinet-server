package com.server.d2ackserver.domain.authentication.sms.service;

import com.server.d2ackserver.domain.authentication.sms.dto.request.SmsRequest;
import com.server.d2ackserver.global.util.SmsCertificationUtil;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class SmsServiceImpl implements SmsService {

    private final SmsCertificationUtil smsCertificationUtil;

    @Override
    public void SendSms(SmsRequest smsRequest) {
        String phoneNum = smsRequest.phoneNum();
        String certificationCode = Integer.toString((int)(Math.random() * (999999 - 100000 + 1)) + 100000);
        smsCertificationUtil.sendSMS(phoneNum, certificationCode);
    }
}
