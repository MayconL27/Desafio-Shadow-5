package com.shadow.desafio.exceptions;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ResponseStatus(HttpStatus.NOT_FOUND)
@ControllerAdvice
@Slf4j
public class UsuariosExceptions extends ResponseEntityExceptionHandler {/* Classe responsavel por tratar todas as exceptions */



}
