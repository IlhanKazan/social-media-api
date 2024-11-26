package org.example.socialmediaapi.controller;

import org.example.socialmediaapi.dto.request.Request;
import org.example.socialmediaapi.dto.response.Response;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import javax.validation.Valid;

public abstract class BaseController<T extends Request, R extends Response> {

    public abstract R save(@Valid @RequestBody T request);
    public abstract R update(@PathVariable(required = true, value = "id") Long id, @Valid @RequestBody T request);
    public abstract R delete(@PathVariable(required = true, value = "id") Long id);

}



