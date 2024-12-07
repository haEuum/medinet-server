package com.server.d2ackserver.domain.todo.dto.response;

import com.server.d2ackserver.domain.todo.domain.entity.ToDo;

public record DeleteToDoResponse(
        Long id,
        String title,
        String description,
        Long priority
        // TODO: 기한
) {
    public static DeleteToDoResponse of(ToDo toDo) {
        return new DeleteToDoResponse(toDo.getId(), toDo.getTitle(), toDo.getDescription(), toDo.getPriority());
    }
}
