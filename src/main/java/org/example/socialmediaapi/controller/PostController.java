package org.example.socialmediaapi.controller;

import org.example.socialmediaapi.dto.request.PostRequest;
import org.example.socialmediaapi.dto.response.PostResponse;
import org.example.socialmediaapi.manager.PostManager;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/post")
public class PostController implements Controller<PostRequest, PostResponse>{

    private final PostManager postManager;

    public PostController(PostManager postManager) {
        this.postManager = postManager;
    }

    @Override
    @Validated
    @PostMapping("/save")
    public PostResponse save(PostRequest postRequest) {
        return postManager.save(postRequest);
    }

    @Override
    public PostResponse update(Long id, PostRequest newInfo) {
        return null;
    }

    @Override
    public PostResponse delete(Long id) {
        return null;
    }

}
