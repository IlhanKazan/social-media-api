package org.example.socialmediaapi.service;

import org.example.socialmediaapi.dto.request.PostRequest;
import org.example.socialmediaapi.dto.response.PagedResponse;
import org.example.socialmediaapi.dto.response.PostResponse;
import org.example.socialmediaapi.entity.Post;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface PostService extends Service<PostRequest, PostResponse>{
    PostResponse getById(Long id);
    PagedResponse<PostResponse> getAll(int page, int size);
    PagedResponse<PostResponse> getAllPostsOfUser(int page, int size, Long id);
}
