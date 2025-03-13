package com.server.d2ackserver.domain.file.exception;

import com.server.d2ackserver.global.exception.CustomError;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

@Getter
@RequiredArgsConstructor
public enum FileError implements CustomError {
    FILE_CREATE_FAIL(HttpStatus.BAD_REQUEST, "failed to create file")
    ;

    private final HttpStatus status;
    private final String message;
}
