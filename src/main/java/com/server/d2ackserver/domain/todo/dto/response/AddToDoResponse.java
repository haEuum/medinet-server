package com.server.d2ackserver.domain.todo.dto.response;

import com.server.d2ackserver.domain.todo.domain.entity.ToDo;
import com.server.d2ackserver.domain.user.domain.enitty.UserEntity;

import java.time.LocalDate;
import java.time.LocalDateTime;

public record AddToDoResponse(
        Long id,
        UserEntity author,
        String title,
        String description,
        LocalDate dueDate,
        LocalDateTime createdAt,
        LocalDateTime updatedAt
        // TODO: 기한
) {
    public static AddToDoResponse of(ToDo toDo) {
        return new AddToDoResponse(toDo.getId(), toDo.getAuthor(), toDo.getTitle(), toDo.getDescription(), toDo.getDueDate(), toDo.getCreatedAt(), toDo.getUpdatedAt());
    }
}
