package com.server.d2ackserver.domain.oauth.dto.request;

import com.server.d2ackserver.domain.user.domain.enums.UserClass;
import com.server.d2ackserver.domain.user.domain.enums.UserField;

public record OAuthAdditionalInfo(
        String phoneNumber,
        Long BiometricAuthNum,
        UserField field,
        UserClass userClass
) {
}
