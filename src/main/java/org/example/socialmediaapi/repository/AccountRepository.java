package org.example.socialmediaapi.repository;

import org.example.socialmediaapi.entity.Account;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AccountRepository extends JpaRepository<Account, Long> {
    boolean existsByEmail(String email);
    boolean existsByUsername(String username);
    boolean existsByPhone(String phone);
    Account findByAccountIdAndStatus(Long accountId, int status);
    List<Account> findAllByStatus(int status);
    Account findByUsernameAndStatus(String username, int status);
}