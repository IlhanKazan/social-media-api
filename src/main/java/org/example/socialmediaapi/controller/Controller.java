package org.example.socialmediaapi.controller;

import org.example.socialmediaapi.dto.request.Request;
import org.example.socialmediaapi.dto.response.Response;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import jakarta.validation.Valid;
import jakarta.servlet.http.HttpServletRequest;

public interface Controller<T extends Request, R extends Response>{
    R save(@Valid @RequestBody T Request, HttpServletRequest httpServletRequest);
    R update(@PathVariable(required = true, value = "id") Long id, @Valid @RequestBody T newInfo);
    R delete(@PathVariable(required = true, value = "id") Long id);
}
