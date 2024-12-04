package org.example.socialmediaapi.manager;

import org.example.socialmediaapi.dto.request.AccountRequest;
import org.example.socialmediaapi.dto.response.AccountResponse;
import org.example.socialmediaapi.entity.Account;
import org.example.socialmediaapi.entity.Interaction;
import org.example.socialmediaapi.entity.Post;
import org.example.socialmediaapi.mappers.AccountMapper;
import org.example.socialmediaapi.repository.AccountRepository;
import org.example.socialmediaapi.service.AccountService;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class AccountManager {

    private final AccountService accountService;
    private final AccountRepository accountRepository;
    private final PostManager postManager;
    private final InteractionManager interactionManager;
    private final AccountMapper accountMapper;

    public AccountManager(AccountService accountService, AccountRepository accountRepository, PostManager postManager, InteractionManager interactionManager, AccountMapper accountMapper) {
        this.accountService = accountService;
        this.accountRepository = accountRepository;
        this.postManager = postManager;
        this.interactionManager = interactionManager;
        this.accountMapper = accountMapper;
    }

    public AccountResponse save(AccountRequest accountRequest) {
        return accountService.save(accountRequest);
    }

    public AccountResponse update(Long id, AccountRequest newInfo) {
        return accountService.update(id, newInfo);
    }

    public AccountResponse delete(long id) {
        Account account = accountRepository.getById(id);
        for (Post post : account.getPosts()) {
            if (post != null)
                postManager.delete(Long.valueOf(post.getPostId()));
        }
        for (Interaction interaction : account.getInteractions()) {
            if (interaction != null)
                interactionManager.delete(Long.valueOf(interaction.getInteractionId()));
        }
        return accountService.delete(id);
    }

    public AccountResponse getById(Long id) {
        return accountService.getById(id);
    }

    public List<AccountResponse> getAll(){
        return accountService.getAll();
    }

    public AccountResponse getByUsername(String username) {
        return accountMapper.accountToResponse(accountRepository.findByUsername(username));
    }
}
