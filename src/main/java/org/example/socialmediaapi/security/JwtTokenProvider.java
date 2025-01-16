package org.example.socialmediaapi.security;

import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.SignatureAlgorithm;
import org.example.socialmediaapi.entity.CustomAccountDetails;
import org.example.socialmediaapi.service.impl.AccountDetailsServiceImpl;
import org.example.socialmediaapi.service.TokenRevocationService;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class JwtTokenProvider {

    private final String secretKey = "ilhan_secret_key";
    private final long validityInMilliseconds = 1800000;
    private final AccountDetailsServiceImpl accountDetailsServiceImpl;
    private final TokenRevocationService tokenRevocationService;

    public JwtTokenProvider(AccountDetailsServiceImpl accountDetailsServiceImpl, TokenRevocationService tokenRevocationService) {
        this.accountDetailsServiceImpl = accountDetailsServiceImpl;
        this.tokenRevocationService = tokenRevocationService;
    }

    public boolean validateToken(String token) {
        try {
            Jwts.parser().setSigningKey(secretKey).parseClaimsJws(token);
            if (tokenRevocationService.isTokenBlacklisted(token)) {
                return false;
            }
            return true;
        } catch (JwtException | IllegalArgumentException e) {
            throw new JwtException("Expired or invalid JWT token");
        }
    }


    public String createToken(UserDetails userDetails) {
        if (!(userDetails instanceof CustomAccountDetails)) {
            throw new IllegalArgumentException("UserDetails must be an instance of CustomAccountDetails");
        }


        CustomAccountDetails customUserDetails = (CustomAccountDetails) accountDetailsServiceImpl.loadUserByUsername(userDetails.getUsername());

        int accountId = customUserDetails.getAccountId();
        String role = customUserDetails.getAuthorities().stream()
                .findFirst()
                .orElseThrow(() -> new RuntimeException("Role not found"))
                .getAuthority();

        Date now = new Date();
        Date validity = new Date(now.getTime() + validityInMilliseconds);

        return Jwts.builder()
                .setSubject(String.valueOf(accountId))
                .claim("role", role)
                .setIssuedAt(now)
                .setExpiration(validity)
                .signWith(SignatureAlgorithm.HS256, secretKey)
                .compact();
    }

    public int getAccountIdFromToken(String token) {
        Claims claims = Jwts.parser()
                .setSigningKey(secretKey)
                .parseClaimsJws(token)
                .getBody();
        return Integer.parseInt(claims.getSubject());
    }
}
