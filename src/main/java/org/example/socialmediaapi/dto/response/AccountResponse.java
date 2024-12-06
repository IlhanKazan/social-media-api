package org.example.socialmediaapi.dto.response;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;
import org.example.socialmediaapi.entity.Interaction;
import org.example.socialmediaapi.entity.Post;
import java.util.Date;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class AccountResponse extends Response {
    private int accountId;
    private String username;
    private String password;
    private String email;
    private String phone;
    private int status;
    private Date createDate;
    private Date updateDate;
    private List<Post> posts;
//    @JsonIgnore
    private List<Interaction> interactions;
}
