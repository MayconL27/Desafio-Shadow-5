package com.shadow.desafio.service;

import com.shadow.desafio.entities.Usuarios;
import com.shadow.desafio.repositories.UsuariosRepository;
import com.shadow.desafio.repositories.feign.AuthFeign;
import com.shadow.desafio.service.exceptions.EntityNotFoundException;
import com.shadow.desafio.util.ValidarCPF;
import jakarta.transaction.Transactional;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Formatter;
import java.util.List;
import java.util.UUID;

@Service
@Data
public class UsuariosService {

    final UsuariosRepository usuariosRepository;
    final PasswordEncoder passwordEncoder;
    @Autowired
    AuthFeign authFeign;

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

    public boolean ValidarToken(String token) {
        return authFeign.validarToken(token);
    }
    public String getTipoUsuario(String token) {
        return authFeign.getTipoUsuario(token);
    }


    public boolean cpfValido(String cpf) {
        boolean cpfValido = ValidarCPF.isCPF(cpf);
        return cpfValido;
    }
}
