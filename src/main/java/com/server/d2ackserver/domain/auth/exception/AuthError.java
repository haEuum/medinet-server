package com.server.d2ackserver.domain.auth.exception;

import com.server.d2ackserver.global.exception.CustomError;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

@Getter
@RequiredArgsConstructor
public enum AuthError implements CustomError {
    USER_NOT_FOUND(HttpStatus.NOT_FOUND,"user not found"),
    PASSWORD_WRONG(HttpStatus.BAD_REQUEST,"password wrong"),
    ;

    private final HttpStatus status;
    private final String message;

}
