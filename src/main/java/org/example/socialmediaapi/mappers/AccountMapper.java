package org.example.socialmediaapi.mappers;

import org.example.socialmediaapi.dto.request.AccountRequest;
import org.example.socialmediaapi.dto.response.AccountResponse;
import org.example.socialmediaapi.entity.Account;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import java.util.List;

@Mapper(componentModel = "spring")
public interface AccountMapper {
    Account requestToAccount(AccountRequest accountRequest);
    @Mapping(source = "username", target = "username")
    @Mapping(source = "password", target = "password")
    @Mapping(source = "email", target = "email")
    @Mapping(source = "phone", target = "phone")
    @Mapping(source = "interactions", target = "interactions")
    AccountResponse accountToResponse(Account account);
    Account responseToAccount(AccountResponse accountResponse);
    List<AccountResponse> accountsToResponses(List<Account> accounts);
}
