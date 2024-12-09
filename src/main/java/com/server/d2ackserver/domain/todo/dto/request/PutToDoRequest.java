package com.server.d2ackserver.domain.todo.dto.request;

import java.time.LocalDate;

public record PutToDoRequest(
        String title,
        String description,
        Long priority,
        LocalDate dueDate
) {
}
