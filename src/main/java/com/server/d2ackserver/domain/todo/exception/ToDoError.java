package com.server.d2ackserver.domain.todo.exception;

import com.server.d2ackserver.global.exception.CustomError;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.springframework.http.HttpStatus;

@RequiredArgsConstructor
@Getter
public enum ToDoError implements CustomError {
    TODO_NOT_FOUND(HttpStatus.BAD_REQUEST, "toDo not found")
    ;

    private final HttpStatus status;
    private final String message;

}
