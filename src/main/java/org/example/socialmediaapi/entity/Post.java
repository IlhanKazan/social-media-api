package org.example.socialmediaapi.entity;

import com.fasterxml.jackson.annotation.JsonGetter;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.*;
import org.hibernate.annotations.Where;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "POSTS")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class Post extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "POSTID", unique = true, nullable = false)
    private int postId;

    @Column(name = "ACCOUNTID", insertable = false, updatable = false)
    private int accountId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ACCOUNTID", nullable = false, referencedColumnName = "ACCOUNTID")
    @JsonIgnore
    @NotNull
    private Account account;

    @Column(name = "CONTEXT", length = 255)
    @NotEmpty
    private String context;

    @OneToMany(mappedBy = "post", fetch = FetchType.LAZY)
    @Where(clause = "STATUS = 1")
    @JsonIgnoreProperties({"post", "account"})
    private List<Interaction> interactions;

}
