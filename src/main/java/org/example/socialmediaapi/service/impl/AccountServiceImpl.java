package org.example.socialmediaapi.service.impl;

import org.example.socialmediaapi.constants.Status;
import org.example.socialmediaapi.dto.request.AccountRequest;
import org.example.socialmediaapi.dto.response.AccountResponse;
import org.example.socialmediaapi.dto.response.AdminAccountResponse;
import org.example.socialmediaapi.dto.response.WebAccountResponse;
import org.example.socialmediaapi.entity.Account;
import org.example.socialmediaapi.mappers.AccountMapper;
import org.example.socialmediaapi.repository.AccountRepository;
import org.example.socialmediaapi.service.AbstractService;
import org.example.socialmediaapi.service.AccountService;
import org.springframework.cache.annotation.*;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.security.crypto.password.PasswordEncoder;
import java.util.Date;
import java.util.List;

@CacheConfig(cacheNames = "accounts")
@Service
public class AccountServiceImpl extends AbstractService implements AccountService {

    private final AccountRepository accountRepository;
    private final AccountMapper accountMapper;
    private final PasswordEncoder passwordEncoder;

    public AccountServiceImpl(AccountRepository accountRepository, AccountMapper accountMapper, PasswordEncoder passwordEncoder) {
        this.accountRepository = accountRepository;
        this.accountMapper = accountMapper;
        this.passwordEncoder = passwordEncoder;
    }

    @Caching(
            put = {
                    @CachePut(key = "#result.accountId"),
                    @CachePut(value = "allAccounts", key = "#result.accountId"),
            }
    )
    @Override
    @Transactional
    public AccountResponse save(AccountRequest request) {
        Account account = accountMapper.requestToAccount(request);
        account.setPassword(passwordEncoder.encode(account.getPassword()));
        account.setRoleId(1);
        account.setStatus(1);
        account.setCreateDate(new Date());
        accountRepository.save(account);
        return accountMapper.accountToResponse(account);
    }

    @Caching(
            put = {
                    @CachePut(key = "#result.accountId"),
                    @CachePut(value = "allAccounts", key = "#result.accountId"),
                    @CachePut(value = "accountByUsername", key = "#result.username")
            }
    )
    @Override
    @Transactional
    public AccountResponse update(AccountRequest newInfo) {
        Account oldAccount = accountRepository.findByAccountIdAndStatus(Long.valueOf(newInfo.getAccountId()), Status.ACTIVE.getValue());
        Account newAccount = accountMapper.requestToAccount(newInfo);
        oldAccount.setUsername(newAccount.getUsername());
        if (oldAccount.getPassword().equals(newAccount.getPassword())) {
            oldAccount.setPassword(newAccount.getPassword());
        }
        else{
            oldAccount.setPassword(passwordEncoder.encode(newAccount.getPassword()));
        }
        oldAccount.setEmail(newAccount.getEmail());
        oldAccount.setPhone(newAccount.getPhone());
        oldAccount.setUpdateDate(new Date());
        accountRepository.save(oldAccount);
        System.out.println(newAccount.getPassword());
        return accountMapper.accountToResponse(oldAccount);
    }

    @Caching(
            evict = {
                    @CacheEvict(key = "#result.accountId"),
                    @CacheEvict(value = "allAccounts", key = "#result.accountId"),
                    @CacheEvict(value = "accountByUsername", key = "#result.username")
            }
    )
    @Override
    @Transactional
    public AccountResponse delete(Long id) {
        Account account = accountRepository.getById(id);
        account.setStatus(0);
        account.setUpdateDate(new Date());
        accountRepository.save(account);
        return accountMapper.accountToResponse(account);
    }

    @CacheEvict(value = "account", key = "#result.accountId")
    @Transactional
    public AccountResponse userDelete(Long id) {
        Account account = accountRepository.getById(id);
        account.setStatus(0);
        account.setUpdateDate(new Date());
        accountRepository.save(account);
        return accountMapper.accountToResponse(account);
    }

    @Cacheable(key = "#id", unless = "#result == null")
    @Override
    public AccountResponse getById(Long id) {
        return accountMapper.accountToResponse(accountRepository.findByAccountIdAndStatus(id, Status.ACTIVE.getValue()));
    }

    @Cacheable(key = "#id", unless = "#result == null")
    @Override
    public WebAccountResponse webGetById(Long id) {
        return accountMapper.accountToWebAccountResponse(accountRepository.findByAccountIdAndStatus(id, Status.ACTIVE.getValue()));
    }

    @Cacheable(value = "allAccounts", unless = "#result == null")
    @Override
    public List<WebAccountResponse> getAll() {
        return accountMapper.accountsToWebAccountResponses(accountRepository.findAllByStatus(Status.ACTIVE.getValue()));
    }

    @Cacheable(value = "adminAccounts", unless = "#result == null")
    @Override
    public List<AdminAccountResponse> adminGetAll() {
        return accountMapper.accountsToAdminAccountResponses(accountRepository.findAllByStatus(Status.ACTIVE.getValue()));
    }

    @Cacheable(value = "accountByUsername", key = "#username", unless = "#result == null || #username == null")
    @Override
    public WebAccountResponse getByUsername(String username) {
        return accountMapper.accountToWebAccountResponse(accountRepository.findByUsernameAndStatus(username, Status.ACTIVE.getValue()));
    }

    @Cacheable(value = "adminAccountByUsername", key = "#result.username", unless = "#result == null || #result.username == null")
    @Override
    public AdminAccountResponse adminGetByUsername(String username) {
        return accountMapper.accountToAdminAccountResponse(accountRepository.findByUsernameAndStatus(username, Status.ACTIVE.getValue()));
    }

    @Override
    public AdminAccountResponse changePassword(Long id) {
        return accountMapper.accountToAdminAccountResponse(accountRepository.findByAccountIdAndStatus(id, Status.ACTIVE.getValue()));
    }

    @Override
    public boolean validateCredentials(String username, String password) {
        AdminAccountResponse adminAccountResponse = loadUserByUsername(username);
        return passwordEncoder.matches(password, adminAccountResponse.getPassword());
    }

    public AdminAccountResponse loadUserByUsername(String username) {
        AdminAccountResponse response = adminGetByUsername(username);
        if (response == null) {
            throw new UsernameNotFoundException("User not found");
        }
        return response;
    }

    public String getRole(Long id){
        return String.valueOf((accountRepository.findByAccountIdAndStatus(id, Status.ACTIVE.getValue())).getRole());
    }
}
