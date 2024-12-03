package org.example.socialmediaapi.dto.request;

import jakarta.validation.constraints.NotNull;
import lombok.*;

import javax.validation.constraints.NotEmpty;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class PostRequest extends Request{

    @NotNull
    private int accountId;

    @NotEmpty
    private String context;

}