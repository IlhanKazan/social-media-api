package org.example.socialmediaapi.service.impl;

import org.example.socialmediaapi.dto.request.InteractionRequest;
import org.example.socialmediaapi.dto.response.InteractionResponse;
import org.example.socialmediaapi.entity.Interaction;
import org.example.socialmediaapi.mappers.InteractionMapper;
import org.example.socialmediaapi.repository.InteractionRepository;
import org.example.socialmediaapi.service.AbstractService;
import org.example.socialmediaapi.service.InteractionService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

@Service
public class InteractionServiceImpl extends AbstractService implements InteractionService {

    private final InteractionRepository interactionRepository;
    private final InteractionMapper interactionMapper;

    public InteractionServiceImpl(InteractionRepository interactionRepository, InteractionMapper interactionMapper) {
        this.interactionRepository = interactionRepository;
        this.interactionMapper = interactionMapper;
    }

    @Override
    @Transactional
    public InteractionResponse save(InteractionRequest request) {
        Interaction interaction = interactionMapper.requestToInteraction(request);
        interaction.setStatus(1);
        interaction.setCreateDate(new Date());
        interactionRepository.save(interaction);
        return interactionMapper.interactionToResponse(interaction);
    }

    @Override
    @Transactional
    public InteractionResponse update(Long id, InteractionRequest newInfo) {
        return null;
    }

    @Override
    @Transactional
    public InteractionResponse delete(Long id) {
        return null;
    }

    @Override
    public InteractionResponse getById(long id) {
        return interactionMapper.interactionToResponse(interactionRepository.getById(id));
    }

    @Override
    public List<InteractionResponse> getAll() {
        return interactionMapper.interactionsToResponses(interactionRepository.findAll());
    }
}
