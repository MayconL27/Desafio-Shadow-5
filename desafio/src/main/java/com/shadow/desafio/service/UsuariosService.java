package com.shadow.desafio.service;

import com.shadow.desafio.entities.Usuarios;
import com.shadow.desafio.repositories.UsuariosRepository;
import com.shadow.desafio.service.exceptions.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class UsuariosService {
    @Autowired
    private UsuariosRepository usuariosRepository;
    public Usuarios findById(UUID codigoID) {
        return usuariosRepository.findById(codigoID).orElseThrow(
                () -> new EntityNotFoundException("ID not found " + codigoID));
    }
}
