package org.example.socialmediaapi.manager;

import org.example.socialmediaapi.dto.request.AccountRequest;
import org.example.socialmediaapi.dto.response.AccountResponse;
import org.example.socialmediaapi.entity.Account;
import org.example.socialmediaapi.service.AccountService;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class AccountManager {

    private final AccountService accountService;

    public AccountManager(AccountService accountService) {
        this.accountService = accountService;
    }

    public AccountResponse getById(Long id) {
        return accountService.getById(id);
    }

    public List<Account> getAll(){
        System.out.println("Get all accounts");
        return accountService.getAll();
    }

    public AccountResponse save(AccountRequest accountRequest) {
        return accountService.save(accountRequest);
    }

    public AccountResponse update(Long id, AccountRequest newInfo) {
        return accountService.update(id, newInfo);
    }

    public AccountResponse delete(long id) {
        return accountService.delete(id);
    }
}
