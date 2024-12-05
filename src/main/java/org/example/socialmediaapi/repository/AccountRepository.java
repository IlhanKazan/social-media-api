package org.example.socialmediaapi.repository;

import jakarta.validation.constraints.NotNull;
import org.example.socialmediaapi.constants.Status;
import org.example.socialmediaapi.dto.response.AccountResponse;
import org.example.socialmediaapi.entity.Account;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AccountRepository extends JpaRepository<Account, Long> {
    Account findByAccountIdAndStatus(Long accountId, int status);
    List<Account> findAllByStatus(int status);
    Account findByUsername(String username);
}