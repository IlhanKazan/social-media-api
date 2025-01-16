package org.example.socialmediaapi.dto.response;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;
import org.example.socialmediaapi.entity.Account;
import org.example.socialmediaapi.entity.Interaction;
import java.util.Date;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class PostResponse extends Response {
    private int postId;
    private int accountId;
    private String accountUsername;
    @JsonIgnore
    private Account account;
    private String context;
    private int status;
    private Date createDate;
    private Date updateDate;
    private List<Interaction> interactions;
}
