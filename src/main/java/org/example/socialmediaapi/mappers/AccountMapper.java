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
    @Mapping(source = "status", target = "status")
    @Mapping(source = "createDate", target = "createDate")
    @Mapping(source = "updateDate", target = "updateDate")
    AccountResponse accountToResponse(Account account);
    List<AccountResponse> accountsToResponses(List<Account> accounts);
}
