package org.example.socialmediaapi.security;

import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.SignatureAlgorithm;
import jakarta.servlet.http.HttpServletRequest;
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

    public String resolveToken(HttpServletRequest request) {
        String bearerToken = request.getHeader("Authorization");
        if (bearerToken != null && bearerToken.startsWith("Bearer ")) {
            return bearerToken.substring(7);
        }
        return null;
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
        String phone = customUserDetails.getPhone();
        String email = customUserDetails.getEmail();

        Date now = new Date();
        Date validity = new Date(now.getTime() + validityInMilliseconds);

        return Jwts.builder()
                .setSubject(String.valueOf(accountId))
                .claim("role", role)
                .claim("phone", phone)
                .claim("email", email)
                .setIssuedAt(now)
                .setExpiration(validity)
                .signWith(SignatureAlgorithm.HS256, secretKey)
                .compact();
    }

    public Claims extractAllClaims(String token) {
        return Jwts.parser()
                .setSigningKey(secretKey) // JWT'nin imzasını doğrulamak için gizli anahtar
                .parseClaimsJws(token)
                .getBody();
    }

    public int getAccountIdFromToken(String token) {
        Claims claims = extractAllClaims(token);
        return Integer.parseInt(claims.getSubject());
    }

    public String getPhoneFromToken(String token) {
        Claims claims = extractAllClaims(token);
        return claims.get("email", String.class);
    }

    public String getEmailFromToken(String token) {
        Claims claims = extractAllClaims(token);
        return claims.get("email", String.class);
    }
}
