package com.server.d2ackserver.domain.authentication.sms.service;

import com.server.d2ackserver.domain.authentication.sms.dto.request.SmsRequest;
import com.server.d2ackserver.domain.authentication.sms.dto.response.SmsResponse;

public interface SmsService {
    SmsResponse SendSms(SmsRequest smsRequest);
}
