package org.example.socialmediaapi.entity;

import jakarta.persistence.*;
import lombok.*;
import org.antlr.v4.runtime.misc.NotNull;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.data.annotation.CreatedDate;

import javax.validation.constraints.NotEmpty;
import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Entity
@Table(name = "ACCOUNTS", uniqueConstraints = {
        @UniqueConstraint(columnNames = {"USERID", "USERNAME", "EMAIL", "PHONE"})
})
public class Account {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "USERID", unique = true, nullable = false)
    private int userId;

    @Column(name = "USERNAME", unique = true, length = 15)
    @NotEmpty
    private String username;

    @Column(name = "PASSWORD", length = 15)
    @NotEmpty
    private String password;

    @Column(name = "EMAIL", unique = true, length = 30)
    @NotEmpty
    private String email;

    @Column(name = "PHONE", unique = true, length = 11)
    @NotEmpty
    private String phone;

    @Column(name = "STATUS", nullable = false, columnDefinition = "INT DEFAULT 1")
    private int status;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "CREATEDATE", columnDefinition = "CURRENT_TIMESTAMP()")
    @CreationTimestamp //dbdeki now() isine yariyo
    private Date createDate;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "UPDATEDATE")
    @UpdateTimestamp
    private Date updateDate;

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
