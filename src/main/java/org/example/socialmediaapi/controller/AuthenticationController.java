package org.example.socialmediaapi.controller;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import org.example.socialmediaapi.dto.request.AccountRequest;
import org.example.socialmediaapi.dto.request.LoginRequest;
import org.example.socialmediaapi.dto.response.AccountResponse;
import org.example.socialmediaapi.dto.response.AuthResponse;
import org.example.socialmediaapi.manager.AccountManager;
import org.example.socialmediaapi.service.TokenRevocationService;
import org.example.socialmediaapi.service.impl.AccountDetailsServiceImpl;
import org.example.socialmediaapi.security.JwtTokenProvider;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
public class AuthenticationController {

    private final AuthenticationManager authenticationManager;
    private final JwtTokenProvider jwtTokenProvider;
    private final AccountManager accountManager;
    private final AccountDetailsServiceImpl accountDetailsServiceImpl;
    private final TokenRevocationService tokenRevocationService;

    public AuthenticationController(AuthenticationManager authenticationManager, JwtTokenProvider jwtTokenProvider, AccountManager accountManager, AccountDetailsServiceImpl accountDetailsServiceImpl, TokenRevocationService tokenRevocationService) {
        this.authenticationManager = authenticationManager;
        this.jwtTokenProvider = jwtTokenProvider;
        this.accountManager = accountManager;
        this.accountDetailsServiceImpl = accountDetailsServiceImpl;
        this.tokenRevocationService = tokenRevocationService;
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@Valid @RequestBody LoginRequest loginRequest) {
        try {

            Authentication authentication = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(loginRequest.getUsername(), loginRequest.getPassword())
            );

            UserDetails userDetails = (UserDetails) authentication.getPrincipal();
            String token = jwtTokenProvider.createToken(userDetails);

            SecurityContextHolder.getContext().setAuthentication(authentication);

            return ResponseEntity.ok(new AuthResponse(token));
        } catch (AuthenticationException e) {
            return ResponseEntity.status(401).body("Invalid credentials");
        }
    }

    @PostMapping("/signup")
    public ResponseEntity<?> signup(@Valid @RequestBody AccountRequest request) {
        AccountResponse response = accountManager.save(request);
        if (response == null) {
            return ResponseEntity.status(401).body("Already existing user");
        } else {
            return ResponseEntity.status(200).body("User registered successfully");
        }
    }

    @PostMapping("/logout")
    public ResponseEntity<?> logout(HttpServletRequest httpServletRequest) {

        SecurityContextHolder.clearContext();

        String token = jwtTokenProvider.resolveToken(httpServletRequest);
        tokenRevocationService.blacklistToken(token);

        return ResponseEntity.ok("Logged out successfully");
    }

}
