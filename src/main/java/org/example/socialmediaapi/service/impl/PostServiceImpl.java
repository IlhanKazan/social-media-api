package org.example.socialmediaapi.service.impl;

import org.example.socialmediaapi.dto.request.PostRequest;
import org.example.socialmediaapi.dto.response.PostResponse;
import org.example.socialmediaapi.entity.Post;
import org.example.socialmediaapi.repository.PostRepository;
import org.example.socialmediaapi.service.AbstractService;
import org.example.socialmediaapi.service.PostService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

@Service
public class PostServiceImpl extends AbstractService implements PostService {

    private final PostRepository postRepository;
    private final ModelMapper modelMapper;

    public PostServiceImpl(PostRepository postRepository, ModelMapper modelMapper) {
        this.postRepository = postRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    @Transactional
    public PostResponse save(PostRequest request) {
        Post post = modelMapper.map(request, Post.class);
        post.setStatus(1);
        post.setCreateDate(new Date());
        postRepository.save(post);
        return modelMapper.map(post, PostResponse.class);
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

        return null;
    }

    @Override
    public List<Post> getAll() {
        return List.of();
    }
}
