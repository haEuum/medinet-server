package com.server.d2ackserver.domain.todo.service;

import com.server.d2ackserver.domain.todo.dto.request.AddToDoRequest;
import com.server.d2ackserver.domain.todo.dto.request.PutToDoRequest;
import com.server.d2ackserver.domain.todo.dto.response.AddToDoResponse;

public interface ToDoService {
    AddToDoResponse addToDo(AddToDoRequest request);
    AddToDoResponse putToDo(PutToDoRequest request);
    void deleteToDo(Long toDoId);
}
