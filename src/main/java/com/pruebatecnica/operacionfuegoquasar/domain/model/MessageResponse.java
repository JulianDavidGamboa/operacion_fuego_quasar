package com.pruebatecnica.operacionfuegoquasar.domain.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.http.HttpStatus;

@Data
@AllArgsConstructor
public class MessageResponse {
    private HttpStatus status;
    private String message;

    /**public ErrorResponse(String message) {
        this.message = message;
    }*/

}
