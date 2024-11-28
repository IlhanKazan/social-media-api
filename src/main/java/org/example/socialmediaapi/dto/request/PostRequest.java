package org.example.socialmediaapi.dto.request;

import lombok.*;
import javax.validation.constraints.NotEmpty;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class PostRequest extends Request{

    @NotEmpty
    private int userId;

    @NotEmpty
    private String context;

}