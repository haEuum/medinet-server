package com.server.d2ackserver.domain.socket.service.impl;

import com.server.d2ackserver.domain.socket.domain.entity.Post;
import com.server.d2ackserver.domain.socket.dto.request.PostRequest;
import com.server.d2ackserver.domain.socket.dto.response.PostResponse;
import com.server.d2ackserver.domain.socket.dto.response.ToUserPostResponse;
import com.server.d2ackserver.domain.socket.repository.PostRepository;
import com.server.d2ackserver.domain.socket.service.PostService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class PostServiceImpl implements PostService {
    private final PostRepository postRepository;


    @Override
    public PostResponse savePost(PostRequest postRequest) {

        Post post = Post.builder()
                .title(postRequest.title())
                .content(postRequest.content())
                .build();


        return PostResponse.of(postRepository.save(post));
    }

    @Override
    public List<ToUserPostResponse> getPosts() {

        List<Post> posts = postRepository.findAll();
        List<ToUserPostResponse> responsePosts = posts.stream().map((Post post) -> ToUserPostResponse.of(PostResponse.of(post))).toList();
        return responsePosts;
    }
}
