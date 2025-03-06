package com.server.d2ackserver.domain.oauth.exception;


import com.server.d2ackserver.global.exception.CustomError;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

@Getter
@RequiredArgsConstructor
public enum OAuthError implements CustomError {
    USER_NOT_FOUND(HttpStatus.NOT_FOUND,"user not found"),
    PASSWORD_WRONG(HttpStatus.BAD_REQUEST,"password wrong"),
    UNSUPPORTED_PROVIDER(HttpStatus.BAD_REQUEST,"unsupported provider"),
    OAUTH_TOKEN_ERROR(HttpStatus.BAD_REQUEST,"oauth token error"),
    OAUTH_USER_INFO_ERROR(HttpStatus.BAD_REQUEST,"oauth user info error"),
    OAUTH_USER_INFO_PARSE_ERROR(HttpStatus.BAD_REQUEST,"oauth user info parse error"),
    ;

    private final HttpStatus status;
    private final String message;

}
