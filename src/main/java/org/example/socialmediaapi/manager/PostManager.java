package org.example.socialmediaapi.manager;

import org.example.socialmediaapi.dto.request.PostRequest;
import org.example.socialmediaapi.dto.response.PostResponse;
import org.example.socialmediaapi.entity.Interaction;
import org.example.socialmediaapi.entity.Post;
import org.example.socialmediaapi.repository.PostRepository;
import org.example.socialmediaapi.service.InteractionService;
import org.example.socialmediaapi.service.PostService;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class PostManager {

    private final PostService postService;
    private final PostRepository postRepository;
    private final InteractionManager interactionManager;

    public PostManager(PostService postService, PostRepository postRepository, InteractionManager interactionManager) {
        this.postService = postService;
        this.postRepository = postRepository;
        this.interactionManager = interactionManager;
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
        return postService.getById(id);
    }

    public List<PostResponse> getAll() {
        return postService.getAll();
    }

}
