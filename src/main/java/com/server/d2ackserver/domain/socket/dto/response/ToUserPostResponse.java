package com.server.d2ackserver.domain.socket.dto.response;

import com.server.d2ackserver.domain.socket.domain.entity.Post;

public record ToUserPostResponse(
        Long id,
        String title,
        String content
) {
    public static ToUserPostResponse of(PostResponse post) {
        return new ToUserPostResponse(post.id(), post.title(), post.content());
    }
}
