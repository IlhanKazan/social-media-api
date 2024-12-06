package org.example.socialmediaapi.service.impl;

import org.example.socialmediaapi.constants.Status;
import org.example.socialmediaapi.dto.request.AccountRequest;
import org.example.socialmediaapi.dto.response.AccountResponse;
import org.example.socialmediaapi.entity.Account;
import org.example.socialmediaapi.mappers.AccountMapper;
import org.example.socialmediaapi.repository.AccountRepository;
import org.example.socialmediaapi.service.AbstractService;
import org.example.socialmediaapi.service.AccountService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.Date;
import java.util.List;

@Service
public class AccountServiceImpl extends AbstractService implements AccountService {

    private final AccountRepository accountRepository;
    private final AccountMapper accountMapper;

    public AccountServiceImpl(AccountRepository accountRepository, AccountMapper accountMapper) {
        this.accountRepository = accountRepository;
        this.accountMapper = accountMapper;
    }

    @Override
    @Transactional
    public AccountResponse save(AccountRequest request) {
        Account account = accountMapper.requestToAccount(request);
        account.setStatus(1);
        account.setCreateDate(new Date());
        accountRepository.save(account);
        return accountMapper.accountToResponse(account);
    }

    @Override
    @Transactional
    public AccountResponse update(Long id, AccountRequest newInfo) {
        Account oldAccount = accountRepository.getById(id);
        Account newAccount = accountMapper.requestToAccount(newInfo);
        oldAccount.setUsername(newAccount.getUsername());
        oldAccount.setPassword(newAccount.getPassword());
        oldAccount.setEmail(newAccount.getEmail());
        oldAccount.setPhone(newAccount.getPhone());
        oldAccount.setUpdateDate(new Date());
        accountRepository.save(oldAccount);
        return accountMapper.accountToResponse(oldAccount);
    }

    @Override
    @Transactional
    public AccountResponse delete(Long id) {
        Account account = accountRepository.getById(id);
        account.setStatus(0);
        account.setUpdateDate(new Date());
        accountRepository.save(account);
        return accountMapper.accountToResponse(account);
    }

    @Override
    public AccountResponse getById(Long id) {
        return accountMapper.accountToResponse(accountRepository.findByAccountIdAndStatus(id, Status.ACTIVE.getValue()));
    }

    @Override
    public List<AccountResponse> getAll() {
        return accountMapper.accountsToResponses(accountRepository.findAllByStatus(Status.ACTIVE.getValue()));
    }

    @Override
    public AccountResponse getByUsername(String username) {
        return accountMapper.accountToResponse(accountRepository.findByUsernameAndStatus(username, Status.ACTIVE.getValue()));
    }
}
