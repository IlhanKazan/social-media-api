package org.example.socialmediaapi.controller;

import org.example.socialmediaapi.dto.request.AccountRequest;
import org.example.socialmediaapi.dto.response.AccountResponse;
import org.example.socialmediaapi.entity.Account;
import org.example.socialmediaapi.manager.AccountManager;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import javax.validation.Valid;
import java.util.List;


@RestController
@RequestMapping("/api/account")
public class AccountController implements Controller<AccountRequest, AccountResponse> {

    private final AccountManager accountManager;

    public AccountController(AccountManager accountManager) {
        this.accountManager = accountManager;
    }

    @GetMapping("/get-by-id/{id}")
    public AccountResponse getById(@PathVariable Long id) {
        return accountManager.getById(id);
    }

    @GetMapping(value = "/get-all")
    public List<Account> getAll() {
        return accountManager.getAll();
    }

    @Override
    @Validated
    @PostMapping("/save")
    public AccountResponse save(AccountRequest request) {
        return accountManager.save(request);
    }

    @Override
    @Validated
    @PostMapping("/update-by-id/{id}")
    public AccountResponse update(@PathVariable Long id, @Valid @RequestBody AccountRequest newInfo) {
        return accountManager.update(id, newInfo);
    }

    @Override
    public AccountResponse delete(Long id) {
        return null;
    }
}
