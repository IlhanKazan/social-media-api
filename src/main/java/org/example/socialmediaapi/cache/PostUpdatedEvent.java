package org.example.socialmediaapi.cache;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class PostUpdatedEvent {

    private final Long postId;

}
