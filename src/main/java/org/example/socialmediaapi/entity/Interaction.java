package org.example.socialmediaapi.entity;

import com.fasterxml.jackson.annotation.JsonGetter;
import com.fasterxml.jackson.annotation.JsonInclude;
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
@Table(name = "INTERACTIONS")
public class Interaction extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "INTERACTIONID", unique = true, nullable = false)
    private int interactionId;

    @Column(name = "ACCOUNTID")
    @NotNull
    @JoinColumn(name = "accountId", referencedColumnName = "accountId")
    private int accountId;

    @Column(name = "POSTID")
    @NotNull
    @JoinColumn(name = "postId", referencedColumnName = "postId")
    private int postId;

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
        }else{
            return "";
        }
    }
}
