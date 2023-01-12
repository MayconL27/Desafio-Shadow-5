package com.shadow.desafio.resources.exceptions;

import com.shadow.desafio.service.exceptions.EntityNotFoundException;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.time.Instant;

@ControllerAdvice
public class ResourceExcepitionHadler {
    @ExceptionHandler(EntityNotFoundException.class) /* Tipo de exception que esta sendo tratada */
    public ResponseEntity<StandarError> entityNotFound(EntityNotFoundException e, HttpServletRequest request) { /* request para ter acesso ao caminho da requisição*/
        StandarError err = new StandarError();
        err.setTimestamp(Instant.now());
        err.setStatus(HttpStatus.NOT_FOUND.value());
        err.setError("Id não encontrado");
        err.setMessage(e.getMessage());
        err.setPath(request.getRequestURI());
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(err);
    }

}
