package org.example.socialmediaapi.dto.response;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@ToString
public class AuthResponse {

    private String token;

    public AuthResponse(String token) {
        this.token = token;
    }

}
