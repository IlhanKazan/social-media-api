package org.example.socialmediaapi.service.impl;

import org.example.socialmediaapi.dto.request.PostRequest;
import org.example.socialmediaapi.dto.response.PostResponse;
import org.example.socialmediaapi.entity.Post;
import org.example.socialmediaapi.mappers.PostMapper;
import org.example.socialmediaapi.repository.PostRepository;
import org.example.socialmediaapi.service.AbstractService;
import org.example.socialmediaapi.service.PostService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

@Service
public class PostServiceImpl extends AbstractService implements PostService {

    private final PostRepository postRepository;
    private final PostMapper postMapper;

    public PostServiceImpl(PostRepository postRepository, PostMapper postMapper) {
        this.postRepository = postRepository;
        this.postMapper = postMapper;
    }

    @Override
    @Transactional
    public PostResponse save(PostRequest request) {
        Post post = postMapper.requestToPost(request);
        post.setStatus(1);
        post.setCreateDate(new Date());
        postRepository.save(post);
        return postMapper.postToResponse(post);
    }

    @Override
    @Transactional
    public PostResponse update(Long id, PostRequest newInfo) {
        return null;
    }

    @Override
    @Transactional
    public PostResponse delete(Long id) {
        return null;
    }

    @Override
    public PostResponse getById(long id) {
        return postMapper.postToResponse(postRepository.getById(id));
    }

    @Override
    @Transactional
    public List<Post> getAll() {
        // List<Post> posts = modelMapper.map(postRepository.getAll(), List<PostResponse>.class);
        return postRepository.getAll();
    }
}
