package org.example.socialmediaapi.service;

import org.example.socialmediaapi.dto.request.Request;
import org.example.socialmediaapi.dto.response.Response;

public interface Service<T extends Request, R extends Response> {
    R save(T request);
    R update(T oldInfo, T newInfo);
    R delete(Long id);
}
