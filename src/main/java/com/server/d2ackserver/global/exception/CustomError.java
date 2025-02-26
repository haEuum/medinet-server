package com.server.d2ackserver.global.exception;


import org.springframework.http.HttpStatus;

public interface CustomError {
    HttpStatus getStatus();
    String getMessage();

}
