package org.example.socialmediaapi.dto.response;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class AccountResponse extends WebAccountResponse {
    private int accountId;
    private String email;
    private String phone;
}
