package com.shadow.desafio.resources.exceptions;

import lombok.Data;

import java.io.Serializable;
import java.time.Instant;
@Data
public class StandarError implements Serializable { /* Classe para armazenar os dados do erro */
    private static final long serialVersionUID = 1L;
    private Instant timestamp;
    private Integer status;
    private String error;
    private String message;
    private String path;

}
