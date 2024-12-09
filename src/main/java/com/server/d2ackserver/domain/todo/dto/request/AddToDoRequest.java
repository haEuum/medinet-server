package com.server.d2ackserver.domain.todo.dto.request;

import java.time.LocalDate;

public record AddToDoRequest(
        String title,
        String description,
        Long priority,
        LocalDate dueDate
) {
}
