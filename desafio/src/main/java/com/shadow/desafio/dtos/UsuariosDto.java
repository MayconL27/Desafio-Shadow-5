package com.shadow.desafio.dtos;

import jakarta.persistence.Column;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class UsuariosDto {
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
