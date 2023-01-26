package com.shadow.desafio.dtos;

import com.shadow.desafio.entities.TipoUsuario;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UsuariosDto {

    private String nome;
    private String cpf;
    private TipoUsuario tipoUsuario;
    private String email;

}
