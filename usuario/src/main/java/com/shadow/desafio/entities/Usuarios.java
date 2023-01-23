package com.shadow.desafio.entities;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;

import lombok.Data;

import java.util.UUID;

@Entity
@Table(name = "usuarios")
@Data
public class Usuarios {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID codigoID;
    @Column(nullable = false,length = 30)
    @NotBlank
    private String nome;
    @Column(nullable = false, unique = true, length = 11)
    @NotBlank
    private String cpf;
    @Column(nullable = false, unique = true, length = 30)
    @NotBlank
    private String email;
    @Column(nullable = false)
    @NotBlank
    private String senha;
    private TipoUsuario tipoUsuario; // CLIENTE, FORNECEDOR, ADMIN;

}
