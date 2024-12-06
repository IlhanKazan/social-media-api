package org.example.socialmediaapi.manager;

import org.example.socialmediaapi.constants.Status;
import org.example.socialmediaapi.dto.request.InteractionRequest;
import org.example.socialmediaapi.dto.response.InteractionResponse;
import org.example.socialmediaapi.mappers.InteractionMapper;
import org.example.socialmediaapi.repository.InteractionRepository;
import org.example.socialmediaapi.service.InteractionService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class InteractionManager {

    private final InteractionService interactionService;
    private final InteractionMapper interactionMapper;

    public InteractionManager(InteractionService interactionService, InteractionMapper interactionMapper) {
        this.interactionService = interactionService;
        this.interactionMapper = interactionMapper;
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

    public List<InteractionResponse> getByType(int type) {
        return interactionService.getByType(type);
    }

}
