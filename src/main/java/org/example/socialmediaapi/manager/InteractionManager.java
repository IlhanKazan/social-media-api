package org.example.socialmediaapi.manager;

import org.example.socialmediaapi.dto.request.InteractionRequest;
import org.example.socialmediaapi.dto.response.InteractionResponse;
import org.example.socialmediaapi.service.InteractionService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class InteractionManager {

    private final InteractionService interactionService;

    public InteractionManager(InteractionService interactionService) {
        this.interactionService = interactionService;
    }

    public InteractionResponse save(InteractionRequest request) {
        return interactionService.save(request);
    }

    public InteractionResponse update(Long id, InteractionRequest request) {
        return interactionService.update(id, request);
    }

    public InteractionResponse delete(Long id) {
        return interactionService.delete(id);
    }

    public InteractionResponse getById(Long id) {
        return interactionService.getById(id);
    }

    public List<InteractionResponse> getAll() {
        return interactionService.getAll();
    }

}
