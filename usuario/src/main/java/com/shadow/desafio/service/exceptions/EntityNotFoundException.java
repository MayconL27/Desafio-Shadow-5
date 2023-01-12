package com.shadow.desafio.service.exceptions;

public class EntityNotFoundException extends RuntimeException {
    public EntityNotFoundException(String msg) { /* Quando a entidade não for encontrada */
        super(msg);
    }
}
