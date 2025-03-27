package org.example.socialmediaapi.service.impl;

import org.example.socialmediaapi.constants.Status;
import org.example.socialmediaapi.dto.request.PostRequest;
import org.example.socialmediaapi.dto.response.PostResponse;
import org.example.socialmediaapi.entity.Post;
import org.example.socialmediaapi.mappers.PostMapper;
import org.example.socialmediaapi.repository.PostRepository;
import org.example.socialmediaapi.service.AbstractService;
import org.example.socialmediaapi.service.PostService;
import org.springframework.cache.annotation.*;
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

    @Caching(
            put = @CachePut(key = "#result.postId"),
            evict = @CacheEvict(value = "allPosts", allEntries = true)
    )
    @Override
    @Transactional
    public PostResponse save(PostRequest request) {
        Post post = postMapper.requestToPost(request);
        post.setStatus(1);
        post.setCreateDate(new Date());
        postRepository.save(post);
        return postMapper.postToResponse(post);
    }

    @Caching(
            put = @CachePut(key = "#result.postId"),
            evict = @CacheEvict(value = "allPosts", allEntries = true)
    )
    @Override
    @Transactional
    public PostResponse update(PostRequest newInfo) {
        Post oldPost = postRepository.getById(Long.valueOf(newInfo.getPostId()));
        Post newPost = postMapper.requestToPost(newInfo);
        oldPost.setContext(newPost.getContext());
        postRepository.save(oldPost);
        return postMapper.postToResponse(oldPost);
    }

    @Caching(
            evict = {
                    @CacheEvict(key = "#id"),
                    @CacheEvict(value = "allPosts", allEntries = true)
            }
    )
    @Override
    @Transactional
    public PostResponse delete(Long id) {
        Post post = postRepository.getById(id);
        post.setStatus(0);
        post.setUpdateDate(new Date());
        postRepository.save(post);
        return postMapper.postToResponse(post);
    }

    @Cacheable(key = "#id", unless = "#result == null")
    @Override
    public PostResponse getById(Long id) {
        Post post = postRepository.findByPostIdAndStatus(id, Status.ACTIVE.getValue());
        return postMapper.postToResponse(post);
    }

    @Cacheable(value = "allPosts", key = "'active'", unless = "#result.isEmpty()")
    @Override
    public List<PostResponse> getAll() {
        List<Post> posts = postRepository.findAllByStatus(Status.ACTIVE.getValue());
        return postMapper.postsToResponses(posts);
    }

}
