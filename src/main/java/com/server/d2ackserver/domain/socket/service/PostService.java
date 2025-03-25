package com.server.d2ackserver.domain.socket.service;

import com.server.d2ackserver.domain.socket.dto.request.PostRequest;
import com.server.d2ackserver.domain.socket.dto.response.PostResponse;
import com.server.d2ackserver.domain.socket.dto.response.ToUserPostResponse;

import java.util.List;

public interface PostService {

    PostResponse savePost(PostRequest postRequest);

    List<ToUserPostResponse> getPosts();

}
