package com.shadow.desafio.service;

import com.shadow.desafio.entities.Usuarios;
import com.shadow.desafio.repositories.UsuariosRepository;
import com.shadow.desafio.service.exceptions.EntityNotFoundException;
import jakarta.transaction.Transactional;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@Data
public class UsuariosService {
    @Autowired
    private UsuariosRepository usuariosRepository;
    public Usuarios findById(UUID codigoID) {
        return usuariosRepository.findById(codigoID).orElseThrow(
                () -> new EntityNotFoundException("ID not found " + codigoID));
    }

    public void delete(Usuarios usuarios) {
        usuariosRepository.delete(usuarios);
    }

    @Transactional /* Caso der errado, garante o rollback */
    public Usuarios save(Usuarios usuarios) {
         return usuariosRepository.save(usuarios);
    }

    public List<Usuarios> findAll() {
        return usuariosRepository.findAll();
    }

}
