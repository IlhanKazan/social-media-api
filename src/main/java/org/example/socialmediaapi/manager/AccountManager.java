package org.example.socialmediaapi.manager;

import jakarta.servlet.http.HttpServletRequest;
import org.example.socialmediaapi.dto.request.AccountRequest;
import org.example.socialmediaapi.dto.response.AccountResponse;
import org.example.socialmediaapi.dto.response.AdminAccountResponse;
import org.example.socialmediaapi.dto.response.WebAccountResponse;
import org.example.socialmediaapi.entity.Account;
import org.example.socialmediaapi.entity.Interaction;
import org.example.socialmediaapi.entity.Post;
import org.example.socialmediaapi.mappers.AccountMapper;
import org.example.socialmediaapi.security.JwtTokenProvider;
import org.example.socialmediaapi.service.AccountService;
import org.example.socialmediaapi.service.InteractionService;
import org.example.socialmediaapi.service.PostService;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class AccountManager {

    private final AccountService accountService;
    private final AccountMapper accountMapper;
    private final PostService postService;
    private final InteractionService interactionService;
    private final JwtTokenProvider jwtTokenProvider;

    public AccountManager(AccountService accountService, AccountMapper accountMapper, PostService postService, InteractionService interactionService, JwtTokenProvider jwtTokenProvider) {
        this.accountService = accountService;
        this.accountMapper = accountMapper;
        this.postService = postService;
        this.interactionService = interactionService;
        this.jwtTokenProvider = jwtTokenProvider;
    }

    public AccountResponse save(AccountRequest accountRequest) {
        String password = accountRequest.getPassword();
        if(password != null && !password.isEmpty()) {
            return accountService.save(accountRequest);
        }
        throw new IllegalArgumentException("Geçersiz Şifre!");
    }

    public AccountResponse update(AccountRequest newInfo, HttpServletRequest httpServletRequest) {
        String token = jwtTokenProvider.resolveToken(httpServletRequest);
        int accountIdFromToken = jwtTokenProvider.getAccountIdFromToken(token);
        Account account = accountMapper.responseToAccount(accountService.getById((long) accountIdFromToken));
        String password = newInfo.getPassword();
        if (password == null || password.isEmpty()) {
            newInfo.setPassword(accountService.adminGetByUsername(account.getUsername()).getPassword());
        }
        newInfo.setAccountId(account.getAccountId());
        return accountService.update(newInfo);
    }

    public AccountResponse delete(Long id) {
        Account account = accountMapper.responseToAccount(accountService.getById((long) id));
        for (Post post : account.getPosts()) {
            if (post != null) {
                postService.delete(Long.valueOf(post.getPostId()));
            }
            for (Interaction interaction : post.getInteractions()) {
                if (interaction != null) {
                    interactionService.delete(Long.valueOf(interaction.getInteractionId()));
                }
            }
        }
        for (Interaction interaction : account.getInteractions()) {
            if (interaction != null) {
                interactionService.delete(Long.valueOf(interaction.getInteractionId()));
            }
        }
        return accountService.delete(Long.valueOf(account.getAccountId()));
    }

    public AccountResponse userDelete(HttpServletRequest httpServletRequest) {
        String token = jwtTokenProvider.resolveToken(httpServletRequest);
        int accountIdFromToken = jwtTokenProvider.getAccountIdFromToken(token);
        Account account = accountMapper.responseToAccount(accountService.getById((long) accountIdFromToken));
        for (Post post : account.getPosts()) {
            if (post != null) {
                postService.delete(Long.valueOf(post.getPostId()));
            }
            for (Interaction interaction : post.getInteractions()) {
                if (interaction != null) {
                    interactionService.delete(Long.valueOf(interaction.getInteractionId()));
                }
            }
        }
        for (Interaction interaction : account.getInteractions()) {
            if (interaction != null) {
                interactionService.delete(Long.valueOf(interaction.getInteractionId()));
            }
        }
        return accountService.delete(Long.valueOf(account.getAccountId()));
    }

    public AccountResponse getById(Long id) {
        return accountService.getById(id);
    }

    public WebAccountResponse webGetById(Long id) {
        return accountService.webGetById(id);
    }

    public List<WebAccountResponse> getAll(){
        return accountService.getAll();
    }

    public List<AdminAccountResponse> adminGetAll() {
        return accountService.adminGetAll();
    }

    public WebAccountResponse getByUsername(String username) {
        return accountService.getByUsername(username);
    }

    public AdminAccountResponse adminGetByUsername(String username) {
        return accountService.adminGetByUsername(username);
    }

    public AdminAccountResponse changePassword(HttpServletRequest httpServletRequest){
        String token = httpServletRequest.getHeader("Authorization").substring(7);
        int accountIdFromToken = jwtTokenProvider.getAccountIdFromToken(token);
        return accountService.changePassword(Long.valueOf(accountIdFromToken));
    }

    public boolean validateCredentials(String username, String password) {
        return accountService.validateCredentials(username, password);
    }

    public AccountResponse loadUserByUsername(String username){
        return accountService.loadUserByUsername(username);
    }

    public String getRole(HttpServletRequest httpServletRequest){
        String token = jwtTokenProvider.resolveToken(httpServletRequest);
        int accountIdFromToken = jwtTokenProvider.getAccountIdFromToken(token);
        return accountService.getRole(Long.valueOf(accountIdFromToken));
    }

}
