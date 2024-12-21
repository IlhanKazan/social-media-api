package org.example.socialmediaapi.manager;

import org.example.socialmediaapi.dto.request.AccountRequest;
import org.example.socialmediaapi.dto.response.AccountResponse;
import org.example.socialmediaapi.dto.response.WebAccountResponse;
import org.example.socialmediaapi.entity.Account;
import org.example.socialmediaapi.entity.Interaction;
import org.example.socialmediaapi.entity.Post;
import org.example.socialmediaapi.mappers.AccountMapper;
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

    public AccountManager(AccountService accountService, AccountMapper accountMapper, PostService postService, InteractionService interactionService) {
        this.accountService = accountService;
        this.accountMapper = accountMapper;
        this.postService = postService;
        this.interactionService = interactionService;
    }

    public AccountResponse save(AccountRequest accountRequest) {
        return accountService.save(accountRequest);
    }

    public AccountResponse update(Long id, AccountRequest newInfo) {
        return accountService.update(id, newInfo);
    }

    public AccountResponse delete(long id) {
        Account account = accountMapper.responseToAccount(accountService.getById(id));
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
        return accountService.delete(id);
    }

    public AccountResponse getById(Long id) {
        return accountService.getById(id);
    }

    public List<WebAccountResponse> getAll(){
        return accountService.getAll();
    }

    public List<AccountResponse> adminGetAll() {
        return accountService.adminGetAll();
    }

    public AccountResponse getByUsername(String username) {
        return accountService.getByUsername(username);
    }

    public boolean validateCredentials(String username, String password) {
        return accountService.validateCredentials(username, password);
    }

    public AccountResponse loadUserByUsername(String username){
        return accountService.loadUserByUsername(username);
    }

}
