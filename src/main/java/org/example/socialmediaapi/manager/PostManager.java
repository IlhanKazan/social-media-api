package org.example.socialmediaapi.manager;

import org.example.socialmediaapi.constants.InteractionType;
import org.example.socialmediaapi.dto.request.PostRequest;
import org.example.socialmediaapi.dto.response.AccountResponse;
import org.example.socialmediaapi.dto.response.PagedResponse;
import org.example.socialmediaapi.dto.response.PostResponse;
import org.example.socialmediaapi.entity.Account;
import org.example.socialmediaapi.entity.Interaction;
import org.example.socialmediaapi.entity.Post;
import org.example.socialmediaapi.mappers.AccountMapper;
import org.example.socialmediaapi.mappers.InteractionMapper;
import org.example.socialmediaapi.mappers.PostMapper;
import org.example.socialmediaapi.security.JwtTokenProvider;
import org.example.socialmediaapi.service.InteractionService;
import org.example.socialmediaapi.service.PostService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import jakarta.servlet.http.HttpServletRequest;
import java.util.List;

@Service
public class PostManager {

    private final PostService postService;
    private final InteractionService interactionService;
    private final InteractionMapper interactionMapper;
    private final AccountManager accountManager;
    private final AccountMapper accountMapper;
    private final PostMapper postMapper;
    private final JwtTokenProvider jwtTokenProvider;


    public PostManager(PostService postService, InteractionService interactionService, InteractionMapper interactionMapper, AccountManager accountManager, AccountMapper accountMapper, PostMapper postMapper, JwtTokenProvider jwtTokenProvider) {
        this.postService = postService;
        this.interactionService = interactionService;
        this.interactionMapper = interactionMapper;
        this.accountManager = accountManager;
        this.accountMapper = accountMapper;
        this.postMapper = postMapper;
        this.jwtTokenProvider = jwtTokenProvider;
    }

    public PostResponse save(PostRequest postRequest, HttpServletRequest httpServletRequest) {
        String token = httpServletRequest.getHeader("Authorization").substring(7);
        int accountIdFromToken = jwtTokenProvider.getAccountIdFromToken(token);
        Account account = accountMapper.responseToAccount(accountManager.getById((long) accountIdFromToken));
        postRequest.setAccount(account);
        postRequest.setAccountId(account.getAccountId());
        return postService.save(postRequest);
    }

    public PostResponse update(PostRequest postRequest, HttpServletRequest httpServletRequest) {
        String token = httpServletRequest.getHeader("Authorization").substring(7);
        int accountIdFromToken = jwtTokenProvider.getAccountIdFromToken(token);
        Account account = accountMapper.responseToAccount(accountManager.getById((long) accountIdFromToken));
        postRequest.setAccount(account);
        postRequest.setAccountId(account.getAccountId());
        return postService.update(postRequest);
    }

    public PostResponse delete(Long id) {
        Post post = postMapper.responseToPost(postService.getById(id));
        if (!post.getInteractions().isEmpty()) {
            for (Interaction interaction : post.getInteractions()) {
                interactionService.delete(Long.valueOf(interaction.getInteractionId()));
            }
        }
        return postService.delete(id);
    }

    public PostResponse userDelete(HttpServletRequest httpServletRequest, Long id) {
        String token = jwtTokenProvider.resolveToken(httpServletRequest);
        int accountIdFromToken = jwtTokenProvider.getAccountIdFromToken(token);
        Post post = postMapper.responseToPost(postService.getById(id));
        if (post.getAccountId() == accountIdFromToken) {
            if (!post.getInteractions().isEmpty()) {
                for (Interaction interaction : post.getInteractions()) {
                    interactionService.delete(Long.valueOf(interaction.getInteractionId()));
                }
            }
            return postService.delete(id);
        } else{
            return null;
        }
    }

    public PostResponse getById(Long id) {
        return postService.getById(id);
    }

    public PagedResponse<PostResponse> getAll(int page, int size) {
        return postService.getAll(page, size);
    }

    public PagedResponse<PostResponse> getAllPostsOfUser(int page, int size, Long id) {
        return postService.getAllPostsOfUser(page, size, id);
    }

    public PostResponse getAllCommentsOfPost(Long postId) {
        Post post = postMapper.responseToPost(postService.getById(postId));
        post.setInteractions(interactionMapper.responsesToInteractions(interactionService.getAllByPostIdAndType(post.getPostId(), InteractionType.COMMENT.getValue())));
        return postMapper.postToResponse(post);
    }

}
