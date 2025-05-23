package org.example.socialmediaapi.dto.response;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ErrorResponse {
    private String errorCode;
    private String message;
    private String field;
}
