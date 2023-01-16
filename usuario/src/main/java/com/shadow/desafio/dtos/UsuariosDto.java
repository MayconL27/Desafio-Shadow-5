package com.shadow.desafio.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UsuariosDto {

    private String nome;
    private String cpf;
    private String email;
    private String senha;
    private String tipoUsuario;
}
