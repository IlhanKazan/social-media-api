package org.example.socialmediaapi.controller;

import jakarta.validation.Valid;
import org.example.socialmediaapi.dto.request.AccountRequest;
import org.example.socialmediaapi.dto.response.AccountResponse;
import org.example.socialmediaapi.manager.AccountManager;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import java.util.List;


@RestController
@RequestMapping("/account")
public class AccountController implements Controller<AccountRequest, AccountResponse> {

    private final AccountManager accountManager;

    public AccountController(AccountManager accountManager) {
        this.accountManager = accountManager;
    }

    @Override
    @Validated
    @PostMapping("/save")
    public AccountResponse save(@Valid @RequestBody AccountRequest request) {
        return accountManager.save(request);
    }

    @Override
    @Validated
    @PostMapping("/update/{id}")
    public AccountResponse update(@PathVariable Long id, @Valid @RequestBody AccountRequest newInfo) {
        return accountManager.update(id, newInfo);
    }

    @Override
    @GetMapping("/delete/{id}")
    public AccountResponse delete(@PathVariable Long id) {
        return accountManager.delete(id);
    }

    @GetMapping("/get-by-id/{id}")
    public AccountResponse getById(@PathVariable Long id) {
        return accountManager.getById(id);
    }

    @GetMapping(value = "/get-all")
    public List<AccountResponse> getAll() { return accountManager.getAll(); }
}
