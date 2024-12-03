package org.example.socialmediaapi.dto.response;

import lombok.*;

import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class InteractionResponse extends Response{
    private int interactionId;
    private int accountId;
    private int postId;
    private String context;
    private int type;
    private int status;
    private Date createDate;
    private Date updateDate;
}
