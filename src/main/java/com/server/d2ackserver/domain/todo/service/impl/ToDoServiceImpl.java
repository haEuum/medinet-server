package com.server.d2ackserver.domain.todo.service.impl;

import com.server.d2ackserver.domain.todo.domain.entity.ToDo;
import com.server.d2ackserver.domain.todo.dto.request.AddToDoRequest;
import com.server.d2ackserver.domain.todo.dto.request.PutToDoRequest;
import com.server.d2ackserver.domain.todo.dto.response.AddToDoResponse;
import com.server.d2ackserver.domain.todo.exception.ToDoError;
import com.server.d2ackserver.domain.todo.repository.ToDoRepository;
import com.server.d2ackserver.domain.todo.service.ToDoService;
import com.server.d2ackserver.global.exception.CustomException;
import com.server.d2ackserver.global.security.holder.SecurityHolder;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ToDoServiceImpl implements ToDoService {
    private final ToDoRepository toDoRepository;
    private final SecurityHolder securityHolder;

    @Override
    public AddToDoResponse addToDo(AddToDoRequest request) {
        ToDo toDo =  ToDo.builder()
                .title(request.title())
                .author(securityHolder.getPrincipal())
                .description(request.description())
                .dueDate(request.dueDate())
                .build();

        return AddToDoResponse.of(toDoRepository.save(toDo));
    }

    @Override
    public AddToDoResponse putToDo(PutToDoRequest request) {
        return null;
    }

    @Override
    public void deleteToDo(Long toDoId) {
        ToDo toDo = toDoRepository.findById(toDoId).orElseThrow(
                () -> new CustomException(ToDoError.TODO_NOT_FOUND)
        );
        toDoRepository.delete(toDo);
    }
}
