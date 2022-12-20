package com.shadow.desafio.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.UUID;
@Entity
@Table(name = "Usuarios")
@Getter
@Setter
public class Usuarios implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID codigoID;
    @Column(nullable = false,length = 30)
    private String nome;
    @Column(nullable = false, unique = true, length = 11)
    private String cpf;
    @Column(nullable = false)
    private String email;
    @Column(nullable = false)
    private String senha;
    @Column(nullable = false)
    private String tipoUsuario;

}
