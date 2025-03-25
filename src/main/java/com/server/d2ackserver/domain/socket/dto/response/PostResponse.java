package com.server.d2ackserver.domain.socket.dto.response;

import com.server.d2ackserver.domain.socket.domain.entity.Post;

import java.time.LocalDateTime;

public record PostResponse(
        Long id,
        String title,
        String content,
        LocalDateTime createdAt,
        LocalDateTime updatedAt
) {
    public static PostResponse of(Post post) {
        return new PostResponse(post.getPostId(), post.getTitle(), post.getContent(), post.getCreatedAt(), post.getUpdatedAt());
    }
}
