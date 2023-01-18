package com.shadow.desafio.service;

import com.shadow.desafio.entities.Usuarios;
import com.shadow.desafio.repositories.UsuariosRepository;
import com.shadow.desafio.service.exceptions.EntityNotFoundException;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@Data
public class UsuariosService {

    final UsuariosRepository usuariosRepository;
    final PasswordEncoder passwordEncoder;

    public Usuarios save(Usuarios usuarios) {
        var encodedPass = this.passwordEncoder.encode(usuarios.getSenha());
        usuarios.setSenha(encodedPass);
        return usuariosRepository.save(usuarios);
    }

    public List<Usuarios> findAll() {
        return usuariosRepository.findAll();
    }

    public Usuarios findById(UUID codigoID) {
        return usuariosRepository.findById(codigoID).orElseThrow(
                () -> new EntityNotFoundException("ID not found " + codigoID));
    }

    public void delete(Usuarios usuarios) {
        usuariosRepository.delete(usuarios);
    }


}
