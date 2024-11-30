package org.example.socialmediaapi.mappers;

import org.example.socialmediaapi.dto.request.AccountRequest;
import org.example.socialmediaapi.dto.response.AccountResponse;
import org.example.socialmediaapi.entity.Account;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface AccountMapper {
    Account requestToAccount(AccountRequest accountRequest);
    AccountResponse accountToResponse(Account account);
}
