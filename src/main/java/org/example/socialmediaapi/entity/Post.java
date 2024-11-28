package org.example.socialmediaapi.entity;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.validation.constraints.NotEmpty;
import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Entity
@Table(name = "POSTS", uniqueConstraints = {
        @UniqueConstraint(columnNames = {"POSTID"})
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

    @Column(name = "STATUS", nullable = false, columnDefinition = "INT DEFAULT 1")
    @NotEmpty
    private int status;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "CREATEDATE", columnDefinition = "CURRENT_TIMESTAMP()")
    @CreationTimestamp
    private Date createDate;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "UPDATEDATE")
    @UpdateTimestamp
    private Date updatedDate;

    @PrePersist
    public void prePersist() {
        if (status != 0 && status != 1) {
            status = 1;
        }
        if (createDate == null) {
            createDate = new Date();
        }
    }

}
