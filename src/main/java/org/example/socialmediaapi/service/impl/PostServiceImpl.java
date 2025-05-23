package org.example.socialmediaapi.service.impl;

import org.example.socialmediaapi.constants.Status;
import org.example.socialmediaapi.dto.request.PostRequest;
import org.example.socialmediaapi.dto.response.PagedResponse;
import org.example.socialmediaapi.dto.response.PostResponse;
import org.example.socialmediaapi.entity.Post;
import org.example.socialmediaapi.mappers.PostMapper;
import org.example.socialmediaapi.repository.PostRepository;
import org.example.socialmediaapi.service.AbstractService;
import org.example.socialmediaapi.service.PostService;
import org.springframework.cache.annotation.*;
import org.springframework.data.domain.*;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.Date;
import java.util.List;

@CacheConfig(cacheNames = "posts")
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
    public PostResponse update(PostRequest newInfo) {
        Post oldPost = postRepository.getById(Long.valueOf(newInfo.getPostId()));
        Post newPost = postMapper.requestToPost(newInfo);
        oldPost.setContext(newPost.getContext());
        postRepository.save(oldPost);
        return postMapper.postToResponse(oldPost);
    }

    @Override
    @Transactional
    public PostResponse delete(Long id) {
        Post post = postRepository.getById(id);
        post.setStatus(0);
        post.setUpdateDate(new Date());
        postRepository.save(post);
        return postMapper.postToResponse(post);
    }

    @Override
    public PostResponse getById(Long id) {
        Post post = postRepository.findByPostIdAndStatus(id, Status.ACTIVE.getValue());
        return postMapper.postToResponse(post);
    }

    @Override
    public PagedResponse<PostResponse> getAll(int page, int size) {
        Pageable pageable = PageRequest.of(page, size, Sort.by("createDate").descending());
        Page<Post> posts = postRepository.findAllByStatus(Status.ACTIVE.getValue(), pageable);
        List<PostResponse> responseList = postMapper.postsToResponses(posts.getContent());
        Page<PostResponse> postResponses = new PageImpl<>(responseList, pageable, posts.getTotalElements());
        return new PagedResponse<>(
                postResponses.getContent(),
                postResponses.getNumber(),
                postResponses.getSize(),
                postResponses.getTotalElements(),
                postResponses.getTotalPages(),
                postResponses.isLast()
        );
    }

    @Override
    public PagedResponse<PostResponse> getAllPostsOfUser(int page, int size, Long id) {
        Pageable pageable = PageRequest.of(page, size, Sort.by("createDate").descending());
        Page<Post> posts = postRepository.findAllByStatusAndAccountId(Status.ACTIVE.getValue(), id, pageable);
        List<PostResponse> responseList = postMapper.postsToResponses(posts.getContent());
        Page<PostResponse> postResponses = new PageImpl<>(responseList, pageable, posts.getTotalElements());
        return new PagedResponse<>(
                postResponses.getContent(),
                postResponses.getNumber(),
                postResponses.getSize(),
                postResponses.getTotalElements(),
                postResponses.getTotalPages(),
                postResponses.isLast()
        );
    }

}
