package com.shadow.desafio.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.util.UUID;

@Entity
@Table(name = "usuarios")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Usuarios {

    @Id
    @GenericGenerator(name="UUIDGenerator", strategy="uuid2")
    @GeneratedValue(generator = "UUIDGenerator")
    private String codigoID;
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

    public Usuarios(String s, String maycon, String cpf, String email, String senha, String cliente) {
    }

}
