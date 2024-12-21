package org.example.socialmediaapi.mappers;

import org.example.socialmediaapi.dto.request.PostRequest;
import org.example.socialmediaapi.dto.response.PostResponse;
import org.example.socialmediaapi.entity.Post;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import java.util.List;

@Mapper(componentModel = "spring")
public interface PostMapper {

    @Mapping(source = "account", target = "account")
    @Mapping(source = "accountId", target = "accountId")
    Post requestToPost(PostRequest postRequest);

    @Mapping(source = "accountId", target = "accountId")
    @Mapping(source = "context", target = "context")
    @Mapping(source = "interactions", target = "interactions")
    @Mapping(source = "account.username", target = "accountUsername")
    PostResponse postToResponse(Post post);

    Post responseToPost(PostResponse postResponse);

    List<PostResponse> postsToResponses(List<Post> posts);
}
