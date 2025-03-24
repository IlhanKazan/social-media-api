package org.example.socialmediaapi.service;

import org.example.socialmediaapi.dto.request.AccountRequest;
import org.example.socialmediaapi.dto.response.AccountResponse;
import org.example.socialmediaapi.dto.response.AdminAccountResponse;
import org.example.socialmediaapi.dto.response.WebAccountResponse;
import java.util.List;

public interface AccountService extends Service<AccountRequest, AccountResponse> {
    AccountResponse getById(Long id);
    List<WebAccountResponse> getAll();
    List<AdminAccountResponse> adminGetAll();
    WebAccountResponse webGetById(Long id);
    WebAccountResponse getByUsername(String username);
    AdminAccountResponse adminGetByUsername(String username);
    AdminAccountResponse changePassword(Long id);
    boolean validateCredentials(String username, String password);
    AccountResponse loadUserByUsername(String username);
}
