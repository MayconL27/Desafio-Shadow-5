package com.shadow.desafio.entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
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
    @Column(nullable = false, length = 30)
    @NotBlank
    private String email;
    @Column(nullable = false)
    @NotBlank
    private String senha;
    @Column(nullable = false, length = 30)
    @NotBlank
    private String tipoUsuario;

}
