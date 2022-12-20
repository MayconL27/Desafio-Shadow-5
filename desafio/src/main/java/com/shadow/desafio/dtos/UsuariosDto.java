package com.shadow.desafio.dtos;

import jakarta.persistence.Column;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

import java.util.UUID;

public class UsuariosDto {

    /* Dependency spring-boot-starter-validation */
    @NotBlank
    private UUID codigoID;
    @NotBlank
    private String nome;
    @NotBlank
    @Size(max = 11)
    private String cpf;
    @NotBlank
    private String email;
    @NotBlank
    private String senha;
    @NotBlank
    private String tipoUsuario;
}
