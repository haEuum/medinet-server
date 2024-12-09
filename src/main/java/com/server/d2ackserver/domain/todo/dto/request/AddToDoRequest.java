package com.server.d2ackserver.domain.todo.dto.request;

public record AddToDoRequest(
        String title,
        String description,
        Long priority
        // TODO: 기한
) {
}
