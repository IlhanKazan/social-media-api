package org.example.socialmediaapi.service.impl;

import org.example.socialmediaapi.constants.InteractionType;
import org.example.socialmediaapi.constants.Status;
import org.example.socialmediaapi.dto.request.InteractionRequest;
import org.example.socialmediaapi.dto.response.InteractionResponse;
import org.example.socialmediaapi.entity.Interaction;
import org.example.socialmediaapi.mappers.InteractionMapper;
import org.example.socialmediaapi.repository.InteractionRepository;
import org.example.socialmediaapi.service.AbstractService;
import org.example.socialmediaapi.service.InteractionService;
import org.springframework.cache.annotation.*;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.Date;
import java.util.List;

@CacheConfig(cacheNames = "interactions")
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
        Interaction oldLike = interactionRepository.findByAccountIdAndPostIdAndTypeAndStatus(interaction.getAccountId(), interaction.getPostId(), InteractionType.LIKE.getValue(), Status.ACTIVE.getValue());
        Interaction oldDislike = interactionRepository.findByAccountIdAndPostIdAndTypeAndStatus(interaction.getAccountId(), interaction.getPostId(), InteractionType.DISLIKE.getValue(), Status.ACTIVE.getValue());
        boolean checkBool = true;
        if (oldLike != null && interaction.getType() == InteractionType.LIKE.getValue()){
            checkBool = false;
        }
        if (oldDislike != null && interaction.getType() == InteractionType.DISLIKE.getValue()){
            checkBool = false;
        }
        if (oldLike != null && interaction.getType() == InteractionType.DISLIKE.getValue()) {
            delete(Long.valueOf(oldLike.getInteractionId()));
        }
        if (oldDislike != null && interaction.getType() == InteractionType.LIKE.getValue()){
            delete(Long.valueOf(oldDislike.getInteractionId()));
        }
        if (checkBool){
            interaction.setStatus(1);
            interaction.setCreateDate(new Date());
            interactionRepository.save(interaction);
            return interactionMapper.interactionToResponse(interaction);
        }
        else{
            return null;
        }
    }

    @Override
    @Transactional
    public InteractionResponse update(InteractionRequest newInfo) {
        Interaction oldInteraction = interactionRepository.getById(Long.valueOf(newInfo.getInteractionId()));
        Interaction newInteraction = interactionMapper.requestToInteraction(newInfo);
        if(oldInteraction.getType() == 0) {
            oldInteraction.setContext(newInteraction.getContext());
            oldInteraction.setUpdateDate(new Date());
            interactionRepository.save(oldInteraction);
        }
        return interactionMapper.interactionToResponse(oldInteraction);
    }

    @Override
    @Transactional
    public InteractionResponse delete(Long id) {
        Interaction interaction = interactionRepository.getById(id);
        interaction.setStatus(0);
        interaction.setUpdateDate(new Date());
        interactionRepository.save(interaction);
        return interactionMapper.interactionToResponse(interaction);
    }

    @Override
    public InteractionResponse getById(Long id) {
        Interaction interaction = interactionRepository.findByInteractionIdAndStatus(Integer.parseInt(String.valueOf(id)), Status.ACTIVE.getValue());
        return interactionMapper.interactionToResponse(interaction);
    }

    @Override
    public List<InteractionResponse> getAll() {
        List<Interaction> interactions = interactionRepository.findAllByStatus(Status.ACTIVE.getValue());
        return interactionMapper.interactionsToResponses(interactions);
    }

    @Override
    public List<InteractionResponse> getByType(int type) {
        List<Interaction> interactions = interactionRepository.findAllByType(type);
        return interactionMapper.interactionsToResponses(interactions);
    }

    @Override
    public List<InteractionResponse> getAllByPostIdAndType(int postId, int type) {
        List<Interaction> interactions = interactionRepository.findAllByPost_PostIdAndType(postId, type);
        return interactionMapper.interactionsToResponses(interactions);
    }

    @Override
    public List<InteractionResponse> getAllByPost(Long postId) {
        List<Interaction> interactions = interactionRepository.findAllByPostIdAndStatus(Integer.parseInt(String.valueOf(postId)), Status.ACTIVE.getValue());
        return interactionMapper.interactionsToResponses(interactions);
    }
}
