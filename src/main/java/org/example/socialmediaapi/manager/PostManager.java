package org.example.socialmediaapi.manager;

import org.example.socialmediaapi.constants.InteractionType;
import org.example.socialmediaapi.constants.Status;
import org.example.socialmediaapi.dto.request.PostRequest;
import org.example.socialmediaapi.dto.response.PostResponse;
import org.example.socialmediaapi.entity.Interaction;
import org.example.socialmediaapi.entity.Post;
import org.example.socialmediaapi.mappers.PostMapper;
import org.example.socialmediaapi.repository.InteractionRepository;
import org.example.socialmediaapi.repository.PostRepository;
import org.example.socialmediaapi.service.PostService;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class PostManager {

    private final PostService postService;
    private final PostRepository postRepository;
    private final InteractionManager interactionManager;
    private final PostMapper postMapper;
    private final InteractionRepository interactionRepository;

    public PostManager(PostService postService, PostRepository postRepository, InteractionManager interactionManager, PostMapper postMapper, InteractionRepository interactionRepository) {
        this.postService = postService;
        this.postRepository = postRepository;
        this.interactionManager = interactionManager;
        this.postMapper = postMapper;
        this.interactionRepository = interactionRepository;
    }

    public PostResponse save(PostRequest postRequest) {
        return postService.save(postRequest);
    }

    public PostResponse update(Long id, PostRequest postRequest) {
        return postService.update(id, postRequest);
    }

    public PostResponse delete(Long id) {
        Post post = postRepository.getById(id);
        if (!post.getInteractions().isEmpty()) {
            for (Interaction interaction : post.getInteractions()) {
                interactionManager.delete(Long.valueOf(interaction.getInteractionId()));
            }
        }
        return postService.delete(id);
    }

    public PostResponse getById(Long id) {
        return postMapper.postToResponse(postRepository.findByAccountIdAndStatus(id, Status.ACTIVE.getValue()));
    }

    public List<PostResponse> getAll() {
        return postMapper.postsToResponses(postRepository.findAllByStatus(Status.ACTIVE.getValue()));
    }

    public PostResponse getAllCommentsOfPost(Long postId) {
        Post post = postRepository.getById(postId);
        post.setInteractions(interactionRepository.findAllByPostIdAndType(post.getPostId(), InteractionType.COMMENT.getValue()));
        return postMapper.postToResponse(post);
    }

}
