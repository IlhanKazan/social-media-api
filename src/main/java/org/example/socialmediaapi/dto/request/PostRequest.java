package org.example.socialmediaapi.dto.request;

import jakarta.validation.constraints.NotEmpty;
import lombok.*;
import org.example.socialmediaapi.entity.Account;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class PostRequest extends Request{

    private Account account;

    private int accountId;

    private int postId;

    @NotEmpty
    private String context;

}