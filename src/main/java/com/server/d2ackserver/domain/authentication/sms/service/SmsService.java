package com.server.d2ackserver.domain.authentication.sms.service;

import com.server.d2ackserver.domain.authentication.sms.dto.request.SmsRequest;

public interface SmsService {
    void SendSms(SmsRequest smsRequest);
}
