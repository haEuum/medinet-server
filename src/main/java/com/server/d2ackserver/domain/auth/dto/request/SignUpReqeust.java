package com.server.d2ackserver.domain.auth.dto.request;

import com.server.d2ackserver.domain.user.domain.enums.UserClass;
import com.server.d2ackserver.domain.user.domain.enums.UserField;
import com.server.d2ackserver.domain.user.domain.enums.UserProvider;

public record SignUpReqeust(
        String name,
        String password,
        String email,
        String phoneNumber,
        UserField field,
        UserClass userClass
) {
}
