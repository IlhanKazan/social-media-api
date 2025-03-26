package org.example.socialmediaapi.controller;

import jakarta.validation.Valid;
import org.example.socialmediaapi.dto.request.AccountRequest;
import org.example.socialmediaapi.dto.response.AccountResponse;
import org.example.socialmediaapi.dto.response.AdminAccountResponse;
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
    @PreAuthorize("hasAnyRole({'ROLE_ADMIN', 'ROLE_USER'})")
    @PostMapping("/save")
    public AccountResponse save(@Valid @RequestBody AccountRequest request, HttpServletRequest httpServletRequest) {
        return accountManager.save(request);
    }

    @Override
    @Validated
    @PreAuthorize("hasAnyRole({'ROLE_ADMIN', 'ROLE_USER'})")
    @PostMapping("/update")
    public AccountResponse update(@Valid @RequestBody AccountRequest newInfo, HttpServletRequest httpServletRequest) {
        return accountManager.update(newInfo, httpServletRequest);
    }

    @Override
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @GetMapping("/admin-delete/{id}")
    public AccountResponse delete(@PathVariable Long id) {
        return accountManager.delete(id);
    }

    @PreAuthorize("hasRole('ROLE_USER')")
    @GetMapping("/delete")
    public AccountResponse userDelete(HttpServletRequest httpServletRequest) {
        return accountManager.userDelete(httpServletRequest);
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @GetMapping("/get-by-id/{id}")
    public AccountResponse getById(@PathVariable Long id) {
        return accountManager.getById(id);
    }

    @PreAuthorize("hasAnyRole({'ROLE_ADMIN', 'ROLE_USER'})")
    @GetMapping("/web-get-by-id/{id}")
    public WebAccountResponse webGetById(@PathVariable Long id) {
        return accountManager.webGetById(id);
    }


    @PreAuthorize("hasAnyRole({'ROLE_ADMIN', 'ROLE_USER'})")
    @GetMapping( "/get-all")
    public List<WebAccountResponse> getAll() {
        return accountManager.getAll();
    }

    @PreAuthorize("hasAnyRole({'ROLE_ADMIN', 'ROLE_USER'})")
    @GetMapping("/get-by-username/{username}")
    public WebAccountResponse getByUsername(@PathVariable String username) {
        return accountManager.getByUsername(username);
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @GetMapping( "/admin-get-all")
    public List<AdminAccountResponse> adminGetAll() {
        return accountManager.adminGetAll();
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @GetMapping( "/admin-get-by-username/{username}")
    public AdminAccountResponse adminGetByUsername(@PathVariable String username) {
        return accountManager.adminGetByUsername(username);
    }

    @PreAuthorize("hasAnyRole({'ROLE_ADMIN', 'ROLE_USER'})")
    @GetMapping( "/change-password")
    public AdminAccountResponse changePassword(HttpServletRequest httpServletRequest) {
        return accountManager.changePassword(httpServletRequest);
    }

    @PreAuthorize("hasAnyRole({'ROLE_ADMIN', 'ROLE_USER'})")
    @GetMapping("/role")
    public String getRole(HttpServletRequest httpServletRequest) {
        return accountManager.getRole(httpServletRequest);
    }

}
