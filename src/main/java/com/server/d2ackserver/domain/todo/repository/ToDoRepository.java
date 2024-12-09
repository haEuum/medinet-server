package com.server.d2ackserver.domain.todo.repository;

import com.server.d2ackserver.domain.todo.domain.entity.ToDo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ToDoRepository extends JpaRepository<ToDo, Long> {
}
