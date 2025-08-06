package com.server.d2ackserver.global.exception;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

@Getter
public class CustomException extends RuntimeException {
  private final CustomError error;

  public CustomException(CustomError error) {
    super(error.getMessage());
    this.error = error;
  }
}
