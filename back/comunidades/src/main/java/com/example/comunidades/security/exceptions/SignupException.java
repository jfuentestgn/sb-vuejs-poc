package com.example.comunidades.security.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.BAD_REQUEST)
public class SignupException extends RuntimeException {

    public SignupException(String message) {
        super(message);
    }
}
