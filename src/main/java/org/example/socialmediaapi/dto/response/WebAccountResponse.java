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
public class WebAccountResponse extends Response {
    private String username;
    private int status;
    private Date createDate;
    private Date updateDate;
    @JsonIgnore
    private List<Post> posts;
    @JsonIgnore
    private List<Interaction> interactions;
}
