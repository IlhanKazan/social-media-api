package org.example.socialmediaapi.controller;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import org.example.socialmediaapi.dto.request.AccountRequest;
import org.example.socialmediaapi.dto.request.LoginRequest;
import org.example.socialmediaapi.dto.response.AccountResponse;
import org.example.socialmediaapi.dto.response.AuthResponse;
import org.example.socialmediaapi.exception.UniqueConstraintViolationException;
import org.example.socialmediaapi.manager.AccountManager;
import org.example.socialmediaapi.service.TokenRevocationService;
import org.example.socialmediaapi.service.impl.AccountDetailsServiceImpl;
import org.example.socialmediaapi.security.JwtTokenProvider;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.example.socialmediaapi.dto.response.ErrorResponse;
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

    public Authentication authGenerator(LoginRequest loginRequest){
        return authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(loginRequest.getUsername(), loginRequest.getPassword())
        );
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@Valid @RequestBody LoginRequest loginRequest) {
        try {

            Authentication authentication = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(
                            loginRequest.getUsername(),
                            loginRequest.getPassword()
                    )
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
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<?> registerUser(@RequestBody @Valid AccountRequest request) {
        try {
            if (request.getPassword() == null || request.getPassword().length() < 6) {
                throw new IllegalArgumentException("Password must be at least 6 characters");
            }
            AccountResponse response = accountManager.save(request);
            return ResponseEntity.status(HttpStatus.CREATED).body("User registered successfully");

        } catch (UniqueConstraintViolationException ex) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body(
                    new ErrorResponse(
                            "REGISTRATION_CONFLICT",
                            ex.getMessage(),
                            ex.getFieldName()
                    )
            );
        } catch (Exception ex) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(
                    new ErrorResponse(
                            "INTERNAL_ERROR",
                            "An error occurred during the registration",
                            null
                    )
            );
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
