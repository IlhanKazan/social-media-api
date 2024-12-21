package org.example.socialmediaapi.service;

import java.util.HashSet;
import java.util.Set;
import org.springframework.stereotype.Service;

@Service
public class TokenRevocationService {

    private Set<String> blacklistedTokens = new HashSet<>();

    public void blacklistToken(String token) {
        blacklistedTokens.add(token);
    }

    public boolean isTokenBlacklisted(String token) {
        return blacklistedTokens.contains(token);
    }
}
