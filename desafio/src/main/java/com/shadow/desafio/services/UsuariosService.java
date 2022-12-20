package com.shadow.desafio.services;

import com.shadow.desafio.repositories.UsuariosRepository;
import org.springframework.stereotype.Service;

@Service
public class UsuariosService {

    /* Ponto de injeção do repository */
    final UsuariosRepository usuariosRepository;
    public UsuariosService(UsuariosRepository usuariosRepository) {
        this.usuariosRepository = usuariosRepository;
    }
}