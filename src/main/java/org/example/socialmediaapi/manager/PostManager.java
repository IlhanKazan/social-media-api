package org.example.socialmediaapi.manager;

import org.example.socialmediaapi.dto.request.PostRequest;
import org.example.socialmediaapi.dto.response.PostResponse;
import org.example.socialmediaapi.entity.Post;
import org.example.socialmediaapi.service.PostService;
import org.springframework.stereotype.Service;
import java.util.List;

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

    public PostResponse getById(Long id) {
        return postService.getById(id);
    }

    public List<Post> getAll() {
        return postService.getAll();
    }

}
