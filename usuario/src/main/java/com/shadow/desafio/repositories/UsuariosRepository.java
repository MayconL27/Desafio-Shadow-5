package com.shadow.desafio.repositories;

import com.shadow.desafio.entities.Usuarios;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;
@Repository
public interface UsuariosRepository extends JpaRepository<Usuarios, UUID> {

}
