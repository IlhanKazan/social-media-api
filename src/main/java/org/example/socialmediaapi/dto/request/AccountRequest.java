package org.example.socialmediaapi.dto.request;

import lombok.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;

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
    @NotEmpty
    private String email;

    @NotEmpty
    private String phone;

}
