package org.example.socialmediaapi.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import lombok.*;
import org.hibernate.annotations.Where;

import javax.validation.constraints.NotEmpty;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Entity
@Table(name = "ACCOUNTS")
public class Account extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ACCOUNTID", unique = true, nullable = false)
    private int accountId;

    @Column(name = "USERNAME", unique = true, length = 15)
    @NotEmpty
    private String username;

    @Column(name = "PASSWORD", length = 15)
    @NotEmpty
    private String password;

    @Column(name = "EMAIL", unique = true, length = 30)
    @Email
    @NotEmpty
    private String email;

    @Column(name = "PHONE", unique = true, length = 11)
    @NotEmpty
    private String phone;

    @OneToMany(mappedBy = "accountId")
    @Where(clause = "STATUS = 1")
    private List<Post> posts;

    @OneToMany(mappedBy = "accountId")
    @Where(clause = "STATUS = 1")
    private List<Interaction> interactions;

}
