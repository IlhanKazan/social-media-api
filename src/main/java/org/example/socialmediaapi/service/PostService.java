package org.example.socialmediaapi.service;

import org.example.socialmediaapi.dto.request.PostRequest;
import org.example.socialmediaapi.dto.response.PostResponse;
import org.example.socialmediaapi.entity.Post;

import java.util.List;

public interface PostService extends Service<PostRequest, PostResponse>{
    PostResponse getById(Long id);
    List<PostResponse> getAll();
}
