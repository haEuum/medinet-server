package com.server.d2ackserver.domain.socket.controller;

import com.server.d2ackserver.domain.socket.domain.entity.Post;
import com.server.d2ackserver.domain.socket.dto.request.PostRequest;
import com.server.d2ackserver.domain.socket.dto.response.ToUserPostResponse;
import com.server.d2ackserver.domain.socket.service.PostService;
import lombok.RequiredArgsConstructor;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class WebSocketController {
    private final SimpMessagingTemplate messagingTemplate;
    private final PostService postService;

    @MessageMapping("/sendPost")
    public void handlePostMessage(PostRequest post) {
        messagingTemplate.convertAndSend("/topic/posts", ToUserPostResponse.of(postService.savePost(post)));
    }
}
