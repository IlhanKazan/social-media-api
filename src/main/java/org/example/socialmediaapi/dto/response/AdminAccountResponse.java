package org.example.socialmediaapi.dto.response;

import lombok.*;
import org.example.socialmediaapi.entity.Role;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class AdminAccountResponse extends AccountResponse {
    private String password;
    private int roleId;
    private Role role;
}
