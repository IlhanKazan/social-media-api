package org.example.socialmediaapi.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Entity
@Table(name = "ROLES")
@JsonIgnoreProperties(value = {"hibernateLazyInitializer", "handler"})
public class Role {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ROLEID", unique = true, nullable = false, columnDefinition = "INT DEFAULT 2")
    private int roleId;

    @Column(name = "role", nullable = false, insertable = false, updatable = false)
    @NotEmpty
    private String role;

    public Role(int roleId) {
        this.roleId = roleId;
    }

}
