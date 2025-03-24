package org.example.socialmediaapi.service.impl;

import org.example.socialmediaapi.constants.Status;
import org.example.socialmediaapi.entity.Account;
import org.example.socialmediaapi.repository.AccountRepository;
import org.example.socialmediaapi.entity.CustomAccountDetails;
import org.example.socialmediaapi.service.AccountDetailsService;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import java.util.Collections;

@Service
public class AccountDetailsServiceImpl implements AccountDetailsService {

    private final AccountRepository accountRepository;

    public AccountDetailsServiceImpl(AccountRepository accountRepository) {
        this.accountRepository = accountRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) {

        Account account = accountRepository.findByUsernameAndStatus(username, Status.ACTIVE.getValue());
        if (account == null) {
            throw new UsernameNotFoundException("Account not found");
        }

        return new CustomAccountDetails(
                account.getUsername(),
                account.getPassword(),
                account.getAccountId(),
                account.getPhone(),
                account.getEmail(),
                Collections.singletonList(new SimpleGrantedAuthority(account.getRole().getRole()))
        );
    }
}
