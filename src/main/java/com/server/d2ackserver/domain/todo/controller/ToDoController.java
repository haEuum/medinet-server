package com.server.d2ackserver.domain.todo.controller;

import com.server.d2ackserver.domain.todo.dto.request.AddToDoRequest;
import com.server.d2ackserver.domain.todo.dto.request.PutToDoRequest;
import com.server.d2ackserver.domain.todo.dto.response.AddToDoResponse;
import com.server.d2ackserver.domain.todo.dto.response.DeleteToDoResponse;
import com.server.d2ackserver.domain.todo.service.ToDoService;
import com.server.d2ackserver.global.response.BaseResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/todo")
@RequiredArgsConstructor
public class ToDoController {
    private final ToDoService toDoService;

    @PostMapping
    public ResponseEntity<BaseResponse<AddToDoResponse>> addToDo(AddToDoRequest request) {
        return BaseResponse.of(toDoService.addToDo(request), 200, "작업 추가 성공");
    }
    // TODO: DTO 만들어야함

    @PutMapping("/{toDoId}/update")
    public ResponseEntity<BaseResponse<AddToDoResponse>> putToDo(@PathVariable Long toDoId ,PutToDoRequest request) {
        return BaseResponse.of(null, 200, "작업 수정 성공"); // TODO: Service 작업 후 연결 요함
    }

    @DeleteMapping("/{toDoId}/delete")
    public ResponseEntity<BaseResponse<Void>> deleteToDo(@PathVariable Long toDoId) {
        toDoService.deleteToDo(toDoId);
        return BaseResponse.of(null  , 200, "작업 삭제 성공");
    }
}
