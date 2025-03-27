package org.example.socialmediaapi.entity;

import com.fasterxml.jackson.annotation.JsonGetter;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import lombok.*;
import org.example.socialmediaapi.constants.Status;
import org.hibernate.annotations.Where;
import jakarta.validation.constraints.NotEmpty;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "ACCOUNTS")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class Account extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ACCOUNTID", unique = true, nullable = false)
    private int accountId;

    @Column(name = "USERNAME", unique = true, length = 15)
    @NotEmpty
    private String username;

    @Column(name = "PASSWORD", length = 100)
    @NotEmpty
    private String password;

    @Column(name = "EMAIL", unique = true, length = 30)
    @Email
    @NotEmpty
    private String email;

    @Column(name = "PHONE", unique = true, length = 11)
    @NotEmpty
    private String phone;

    @Column(name = "ROLEID", columnDefinition = "INT DEFAULT 2")
    private int roleId;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "ROLEID", nullable = false, insertable = false, updatable = false)
    private Role role;

    @OneToMany(mappedBy = "account", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @Where(clause = "STATUS = 1")
    @JsonIgnoreProperties({"account", "interactions"})
    private List<Post> posts;

    @OneToMany(mappedBy = "account", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @Where(clause = "STATUS = 1")
    @JsonIgnoreProperties({"account", "post"})
    private List<Interaction> interactions;

}
