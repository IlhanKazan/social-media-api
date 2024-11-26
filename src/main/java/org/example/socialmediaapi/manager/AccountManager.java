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

    public AccountResponse getByAccountId(Long id) {
        return accountService.getByAccountId(id);
    }

    public List<Account> getAllAccounts(){
        System.out.println("Get all accounts");
        return accountService.getAllAccounts();
    }

    public AccountResponse save(AccountRequest accountRequest) {
        return accountService.save(accountRequest);
    }

    public AccountResponse update(AccountRequest oldInfo, AccountRequest newInfo) {
        return accountService.update(oldInfo, newInfo);
    }

    public AccountResponse delete(long id) {
        return accountService.delete(id);
    }
}
