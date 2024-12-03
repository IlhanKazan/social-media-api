package org.example.socialmediaapi.mappers;

import org.example.socialmediaapi.dto.request.InteractionRequest;
import org.example.socialmediaapi.dto.response.InteractionResponse;
import org.example.socialmediaapi.entity.Interaction;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface InteractionMapper {
    Interaction requestToInteraction(InteractionRequest interactionRequest);
    @Mapping(source = "interactionId", target = "interactionId")
    @Mapping(source = "accountId", target = "accountId")
    @Mapping(source = "postId", target = "postId")
    @Mapping(source = "context", target = "context")
    @Mapping(source = "type", target = "type")
    InteractionResponse interactionToResponse(Interaction interaction);
    List<InteractionResponse> interactionsToResponses(List<Interaction> interactions);
}
