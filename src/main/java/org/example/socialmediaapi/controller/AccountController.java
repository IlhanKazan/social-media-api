package org.example.socialmediaapi.controller;

import jakarta.validation.Valid;
import org.example.socialmediaapi.dto.request.AccountRequest;
import org.example.socialmediaapi.dto.response.AccountResponse;
import org.example.socialmediaapi.dto.response.WebAccountResponse;
import org.example.socialmediaapi.manager.AccountManager;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import jakarta.servlet.http.HttpServletRequest;
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
    public AccountResponse save(@Valid @RequestBody AccountRequest request, HttpServletRequest httpServletRequest) {
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

    @PreAuthorize("hasAnyRole({'ROLE_ADMIN', 'ROLE_USER'})")
    @GetMapping( "/get-all")
    public List<WebAccountResponse> getAll() {
        return accountManager.getAll();
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @GetMapping( "/admin-get-all")
    public List<AccountResponse> adminGetAll() {
        return accountManager.adminGetAll();
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @GetMapping("/get-by-username/{username}")
    public AccountResponse getByUsername(@PathVariable String username) {
        return accountManager.getByUsername(username);
    }
}
