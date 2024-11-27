package org.example.socialmediaapi.controller;

import org.example.socialmediaapi.dto.request.AccountRequest;
import org.example.socialmediaapi.dto.response.AccountResponse;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import javax.validation.Valid;

public interface Controller {
    AccountResponse save(@Valid @RequestBody AccountRequest accountRequest);
    AccountResponse update(@PathVariable(required = true, value = "id") Long id, @Valid @RequestBody AccountRequest newInfo);
    AccountResponse delete(@PathVariable(required = true, value = "id") Long id);
}
