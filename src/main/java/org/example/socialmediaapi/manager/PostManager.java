package org.example.socialmediaapi.manager;

import org.example.socialmediaapi.constants.InteractionType;
import org.example.socialmediaapi.dto.request.PostRequest;
import org.example.socialmediaapi.dto.response.PostResponse;
import org.example.socialmediaapi.entity.Interaction;
import org.example.socialmediaapi.entity.Post;
import org.example.socialmediaapi.mappers.PostMapper;
import org.example.socialmediaapi.service.InteractionService;
import org.example.socialmediaapi.service.PostService;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class PostManager {

    private final PostService postService;
    private final InteractionService interactionService;
    private final PostMapper postMapper;


    public PostManager(PostService postService, InteractionService interactionService, PostMapper postMapper) {
        this.postService = postService;
        this.interactionService = interactionService;
        this.postMapper = postMapper;
    }

    public PostResponse save(PostRequest postRequest) {
        return postService.save(postRequest);
    }

    public PostResponse update(Long id, PostRequest postRequest) {
        return postService.update(id, postRequest);
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

    public PostResponse getById(Long id) {
        return postService.getById(id);
    }

    public List<PostResponse> getAll() {
        return postService.getAll();
    }

    public PostResponse getAllCommentsOfPost(Long postId) {
        Post post = postMapper.responseToPost(postService.getById(postId));
        post.setInteractions(interactionService.getAllByPostIdAndType(post.getPostId(), InteractionType.COMMENT.getValue()));
        return postMapper.postToResponse(post);
    }

}
