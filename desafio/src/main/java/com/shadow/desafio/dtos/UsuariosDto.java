package com.shadow.desafio.dtos;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

import java.util.UUID;
@Getter
@Setter
public class UsuariosDto {

    /* Dependency spring-boot-starter-validation */

    @NotBlank/* Verifica se o campo não é nulo*/
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
