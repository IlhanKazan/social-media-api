package org.example.socialmediaapi.manager;

import org.example.socialmediaapi.dto.request.AccountRequest;
import org.example.socialmediaapi.dto.response.AccountResponse;
import org.example.socialmediaapi.entity.Account;
import org.example.socialmediaapi.entity.Post;
import org.example.socialmediaapi.repository.AccountRepository;
import org.example.socialmediaapi.repository.PostRepository;
import org.example.socialmediaapi.service.AccountService;
import org.example.socialmediaapi.service.PostService;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class AccountManager {

    private final AccountService accountService;
    private final PostService postService;
    private final PostRepository postRepository;
    private final AccountRepository accountRepository;

    public AccountManager(AccountService accountService, PostService postService, PostRepository postRepository, AccountRepository accountRepository) {
        this.accountService = accountService;
        this.postService = postService;
        this.postRepository = postRepository;
        this.accountRepository = accountRepository;
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
            postService.delete(Long.valueOf(post.getPostId()));
        }
        return accountService.delete(id);
    }

    public AccountResponse getById(Long id) {
        return accountService.getById(id);
    }

    public List<AccountResponse> getAll(){ return accountService.getAll(); }
}
