package org.example.socialmediaapi.dto.response;

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
public class WebAccountResponse extends Response {
    private String username;
    private int status;
    private Date createDate;
    private Date updateDate;
    private List<Post> posts;
    private List<Interaction> interactions;
}
