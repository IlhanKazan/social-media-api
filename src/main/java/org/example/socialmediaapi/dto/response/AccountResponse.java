package org.example.socialmediaapi.dto.response;

import lombok.*;
import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class AccountResponse extends Response {
    private int userId;
    private String username;
    private String password;
    private String email;
    private String phone;
    private int status;
    private Date createDate;
    //private List<Post> posts;
}
