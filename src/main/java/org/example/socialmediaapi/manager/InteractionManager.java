package org.example.socialmediaapi.manager;

import jakarta.servlet.http.HttpServletRequest;
import org.example.socialmediaapi.dto.request.InteractionRequest;
import org.example.socialmediaapi.dto.response.InteractionResponse;
import org.example.socialmediaapi.dto.response.PostResponse;
import org.example.socialmediaapi.entity.Account;
import org.example.socialmediaapi.entity.Interaction;
import org.example.socialmediaapi.entity.Post;
import org.example.socialmediaapi.mappers.AccountMapper;
import org.example.socialmediaapi.mappers.InteractionMapper;
import org.example.socialmediaapi.security.JwtTokenProvider;
import org.example.socialmediaapi.service.InteractionService;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Service
public class InteractionManager {

    private final InteractionService interactionService;
    private final InteractionMapper interactionMapper;
    private final AccountManager accountManager;
    private final AccountMapper accountMapper;
    private final JwtTokenProvider jwtTokenProvider;

    public InteractionManager(InteractionService interactionService, InteractionMapper interactionMapper, AccountManager accountManager, AccountMapper accountMapper, JwtTokenProvider jwtTokenProvider) {
        this.interactionService = interactionService;
        this.interactionMapper = interactionMapper;
        this.accountManager = accountManager;
        this.accountMapper = accountMapper;
        this.jwtTokenProvider = jwtTokenProvider;
    }

    public InteractionResponse save(InteractionRequest interactionRequest, HttpServletRequest httpServletRequest) {
        String token = jwtTokenProvider.resolveToken(httpServletRequest);
        int accountIdFromToken = jwtTokenProvider.getAccountIdFromToken(token);
        Account account = accountMapper.responseToAccount(accountManager.getById((long) accountIdFromToken));
        interactionRequest.setAccount(account);
        interactionRequest.setAccountId(account.getAccountId());
        return interactionService.save(interactionRequest);
    }

    public InteractionResponse update(InteractionRequest interactionRequest, HttpServletRequest httpServletRequest) {
        String token = jwtTokenProvider.resolveToken(httpServletRequest);
        int accountIdFromToken = jwtTokenProvider.getAccountIdFromToken(token);
        Account account = accountMapper.responseToAccount(accountManager.getById((long) accountIdFromToken));
        interactionRequest.setAccount(account);
        interactionRequest.setAccountId(account.getAccountId());
        return interactionService.update(interactionRequest);
    }

    public InteractionResponse delete(Long id) {
        return interactionService.delete(id);
    }

    public InteractionResponse userDelete(HttpServletRequest httpServletRequest, Long id) {
        String token = jwtTokenProvider.resolveToken(httpServletRequest);
        int accountIdFromToken = jwtTokenProvider.getAccountIdFromToken(token);
        Interaction interaction = interactionMapper.responseToInteraction(interactionService.getById(id));
        if (interaction.getAccountId() == accountIdFromToken) {
            return interactionService.delete(id);
        } else{
            return null;
        }
    }

    public InteractionResponse getById(Long id) {
        return interactionService.getById(id);
    }

    public List<InteractionResponse> getAll() {
        return interactionService.getAll();
    }

    public List<InteractionResponse> getAllByPost(Long id) {
        return interactionService.getAllByPost(id);
    }

    public List<InteractionResponse> getByType(int type) {
        return interactionService.getByType(type);
    }

}
