package org.example.socialmediaapi.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;

@Configuration
public class WebConfig {

    @Autowired
    private StatusFilterProvider statusFilterProvider;

    @Autowired
    public void configureJackson(ObjectMapper objectMapper) {
        objectMapper.setFilterProvider(statusFilterProvider);
    }
}
