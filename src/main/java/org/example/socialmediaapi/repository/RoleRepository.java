package org.example.socialmediaapi.repository;

import org.example.socialmediaapi.entity.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;

public interface RoleRepository extends JpaRepository<Role, Long> {
    Role findByRole(String role);
}
