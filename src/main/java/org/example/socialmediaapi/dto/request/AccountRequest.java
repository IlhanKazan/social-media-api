package org.example.socialmediaapi.dto.request;

import jakarta.validation.constraints.Digits;
import jakarta.validation.constraints.Email;
import lombok.*;
import jakarta.validation.constraints.NotEmpty;
import org.example.socialmediaapi.validation.ValidPassword;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class AccountRequest extends Request {

    private int accountId;

    @NotEmpty
    private String username;

    @ValidPassword
    private String password;

    @Email
    @NotEmpty(message = "Email cannot be empty")
    private String email;

    @Digits(message="Number should contain 10 digits.", fraction = 0, integer = 10)
    @NotEmpty
    private String phone;

}
