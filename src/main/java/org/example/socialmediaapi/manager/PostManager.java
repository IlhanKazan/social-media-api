package org.example.socialmediaapi.manager;

import org.example.socialmediaapi.dto.request.PostRequest;
import org.example.socialmediaapi.dto.response.PostResponse;
import org.example.socialmediaapi.service.PostService;
import org.springframework.stereotype.Service;

@Service
public class PostManager {

    private final PostService postService;

    public PostManager(PostService postService) {
        this.postService = postService;
    }

    public PostResponse save(PostRequest postRequest) {
        return postService.save(postRequest);
    }

    public PostResponse update(PostRequest postRequest) {
        return null;
    }

    public PostResponse delete(PostRequest postRequest) {
        return null;
    }

}
