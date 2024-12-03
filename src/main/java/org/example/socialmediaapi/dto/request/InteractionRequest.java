package org.example.socialmediaapi.dto.request;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class InteractionRequest extends Request {

    @NotNull
    private int accountId;

    @NotNull
    private int postId;

    @NotEmpty
    private String context;

    @NotNull
    private int type;

}
