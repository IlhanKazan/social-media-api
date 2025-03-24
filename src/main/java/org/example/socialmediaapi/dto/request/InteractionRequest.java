package org.example.socialmediaapi.dto.request;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.*;
import org.example.socialmediaapi.entity.Account;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class InteractionRequest extends Request {

    private int accountId;

    private int interactionId;

    @NotNull
    private int postId;

    @NotEmpty
    private String context;

    @NotNull
    private int type;

    private Account account;


}
