package org.example.socialmediaapi.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Entity
@Table(name = "POSTS")
public class Post extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "POSTID", unique = true, nullable = false)
    private int postId;

    /*@JoinColumn(name = "post_userId", referencedColumnName = "userId")
    private int userId;*/

    @Column(name = "USERID")
    @NotNull
    private int userId;

    @Column(name = "CONTEXT", length = 255)
    @NotEmpty
    private String context;

}
