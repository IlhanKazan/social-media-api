package org.example.socialmediaapi.entity;

import com.fasterxml.jackson.annotation.*;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "INTERACTIONS")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class Interaction extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "INTERACTIONID", unique = true, nullable = false)
    private int interactionId;

    @Column(name = "POSTID")
    @NotNull
    private int postId;

    @Column(name = "ACCOUNTID")
    @NotNull
    private int accountId;

    private String accountUsername;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ACCOUNTID", nullable = false, referencedColumnName = "ACCOUNTID", insertable = false, updatable = false)
    @JsonIgnore
    private Account account;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "POSTID", nullable = false, referencedColumnName = "POSTID", insertable = false, updatable = false)
    @JsonIgnore
    private Post post;

    @Column(name = "CONTEXT")
    @NotEmpty
    @JsonInclude(JsonInclude.Include.NON_EMPTY)
    private String context;

    @Column(name = "TYPE")
    @NotNull
    private int type;

    @JsonGetter("context")
    public String getContext() {
        if (type == 0) {
            return context;
        } else {
            return "";
        }
    }

    @JsonGetter("accountUsername")
    public String getAccountUsername() {
       return account.getUsername();
    }

}
