package com.server.d2ackserver.domain.patient.patient;

import com.server.d2ackserver.global.exception.CustomError;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

@Getter
@RequiredArgsConstructor
public enum PatientError implements CustomError {
    USER_NOT_FOUND(HttpStatus.NOT_FOUND,"user not found"),
    PASSWORD_WRONG(HttpStatus.BAD_REQUEST,"password wrong"),
    FAILED_TO_AGREE_PATIENT(HttpStatus.INTERNAL_SERVER_ERROR,"failed to agree patient"),
    FAILED_TO_DENY_PATIENT(HttpStatus.INTERNAL_SERVER_ERROR,"failed to deny patient"),
    ;

    private final HttpStatus status;
    private final String message;

}

