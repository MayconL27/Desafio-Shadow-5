package com.shadow.desafio.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class Exceptions extends RuntimeException {
    public Exceptions(String message) {
        super(message);
    }
}
