package com.server.d2ackserver.domain.todo.dto.request;

public record PutToDoRequest(
        String title,
        String description,
        Long priority
        // TODO: 기한
) {
}
