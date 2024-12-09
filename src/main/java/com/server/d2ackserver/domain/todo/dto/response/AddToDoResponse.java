package com.server.d2ackserver.domain.todo.dto.response;

import com.server.d2ackserver.domain.todo.domain.entity.ToDo;

public record AddToDoResponse(
        Long id,
        String title,
        String description,
        Long priority
        // TODO: 기한
) {
    public static AddToDoResponse of(ToDo toDo) {
        return new AddToDoResponse(toDo.getId(), toDo.getTitle(), toDo.getDescription(), toDo.getPriority()); // TODO: 기한
    }
}
