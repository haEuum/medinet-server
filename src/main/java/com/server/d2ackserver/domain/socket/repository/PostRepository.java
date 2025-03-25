package com.server.d2ackserver.domain.socket.repository;

import com.server.d2ackserver.domain.socket.domain.entity.Post;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PostRepository extends JpaRepository<Post, Long> {
}
