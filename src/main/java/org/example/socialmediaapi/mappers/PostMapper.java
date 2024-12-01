package org.example.socialmediaapi.mappers;

import org.example.socialmediaapi.dto.request.PostRequest;
import org.example.socialmediaapi.dto.response.PostResponse;
import org.example.socialmediaapi.entity.Post;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface PostMapper {
    @Mapping(source = "userId", target = "userId")
    @Mapping(source = "context", target = "context")
    Post requestToPost(PostRequest postRequest);
    PostResponse postToResponse(Post post);
    List<PostResponse> postsToResponses(List<Post> posts);
}
