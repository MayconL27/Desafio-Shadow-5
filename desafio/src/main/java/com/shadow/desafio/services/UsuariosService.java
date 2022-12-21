package com.shadow.desafio.services;

import com.shadow.desafio.entities.Usuarios;
import com.shadow.desafio.repositories.UsuariosRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

@Service
public class UsuariosService {

    /* Ponto de injeção do repository */
    final UsuariosRepository usuariosRepository;
    public UsuariosService(UsuariosRepository usuariosRepository) {
        this.usuariosRepository = usuariosRepository;
    }

    @Transactional /* caso algo der errado na transação ele dar rollback */
    public Usuarios save(Usuarios usuarios) {
        return usuariosRepository.save(usuarios);
    }
}