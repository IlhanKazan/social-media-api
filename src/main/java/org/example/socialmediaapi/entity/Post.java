package org.example.socialmediaapi.entity;

import com.fasterxml.jackson.annotation.JsonInclude;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.util.List;

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

    @Column(name = "ACCOUNTID")
    @NotNull
    @JoinColumn(name = "accountId", referencedColumnName = "accountId")
    private int accountId;

    @Column(name = "CONTEXT", length = 255)
    @NotEmpty
    private String context;

    @OneToMany(mappedBy = "postId")
    private List<Interaction> interactions;


}
