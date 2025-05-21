package org.example.socialmediaapi.service;

import org.example.socialmediaapi.dto.request.InteractionRequest;
import org.example.socialmediaapi.dto.response.InteractionResponse;
import org.example.socialmediaapi.entity.Interaction;

import java.util.List;

public interface InteractionService extends Service<InteractionRequest, InteractionResponse> {
    InteractionResponse getById(Long id);
    List<InteractionResponse> getAll();
    List<InteractionResponse> getByType(int type);
    List<InteractionResponse> getAllByPostIdAndType(int postId, int type);
    List<InteractionResponse> getAllByPost(Long postId);
}
