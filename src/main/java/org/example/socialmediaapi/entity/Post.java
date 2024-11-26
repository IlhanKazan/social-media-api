package org.example.socialmediaapi.entity;

import jakarta.persistence.*;
import lombok.*;
import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Entity
@Table(name = "POSTS",  uniqueConstraints = {
        @UniqueConstraint(columnNames = {"FLIGHT", "NUMBER"})
})
public class Post {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "POSTID")
    private int postId;

    @ManyToOne
    @JoinColumn(name = "ACCOUNTS", referencedColumnName = "userId")
    private Account userId;

    @Column(name = "CONTEXT", length = 255)
    private String context;

    @Column(name = "STATUS")
    private int status;

    @Column(name = "CREATEDATE")
    private Date createDate;

}
