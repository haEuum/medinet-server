package com.server.d2ackserver.domain.socket.controller;

import com.server.d2ackserver.domain.socket.dto.request.PostRequest;
import com.server.d2ackserver.domain.socket.dto.response.PostResponse;
import com.server.d2ackserver.domain.socket.dto.response.ToUserPostResponse;
import com.server.d2ackserver.domain.socket.service.PostService;
import com.server.d2ackserver.global.response.BaseResponse;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/posts")
public class PostController {
    private final PostService postService;
    private final SimpMessagingTemplate messagingTemplate;


    @GetMapping
    @Operation(summary = "게시글 목록 불러오기")
    public List<ToUserPostResponse> getAllPosts() {
        return postService.getPosts();
    }

    @PostMapping
    @Operation(summary = "게시글 생성")
    public ResponseEntity<BaseResponse<PostResponse>> createPost(@RequestBody PostRequest post) {
        messagingTemplate.convertAndSend("/medinet/posts", post);
        return BaseResponse.of(postService.savePost(post), 200, "게시글 게시 완료") ;
    }
}
