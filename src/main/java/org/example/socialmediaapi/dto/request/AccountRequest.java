package org.example.socialmediaapi.dto.request;

import jakarta.validation.constraints.Email;
import lombok.*;
import jakarta.validation.constraints.NotEmpty;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class AccountRequest extends Request {

    @NotEmpty
    private String username;

    @NotEmpty
    private String password;

    @Email
    @NotEmpty(message = "Email cannot be empty")
    private String email;

    @NotEmpty
    private String phone;

}
