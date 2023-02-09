package com.shadow.desafio.service;

import com.shadow.desafio.dtos.LoginDto;
import com.shadow.desafio.dtos.UsuariosDto;
import com.shadow.desafio.entities.Usuarios;
import com.shadow.desafio.repositories.UsuariosRepository;
import com.shadow.desafio.repositories.feign.AuthFeign;
import com.shadow.desafio.service.exceptions.UserNotFoundException;
import com.shadow.desafio.util.ValidarCPF;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@Data
public class UsuariosService {

    final UsuariosRepository usuariosRepository;
    final PasswordEncoder passwordEncoder;
    @Autowired
    AuthFeign authFeign;

    public Usuarios save(Usuarios usuarios) { // Salvar usuário.
        var encodedPass = this.passwordEncoder.encode(usuarios.getSenha());
        usuarios.setSenha(encodedPass);
        return usuariosRepository.save(usuarios);
    }

    public List<Usuarios> findAll() { // Listar todos os usuários.
        return usuariosRepository.findAll();
    }

    public Usuarios findById(String codigoID) {
        Optional<Usuarios> usuarios = usuariosRepository.findById(codigoID);
        return usuarios.orElseThrow(() -> new UserNotFoundException());
    }

    public void delete(Usuarios usuarios) {
        findById(usuarios.getCodigoID());
        usuariosRepository.delete(usuarios);
    }


    public Object loginUsuario(LoginDto loginDto) {

        Usuarios usuarios = usuariosRepository.findByEmail(loginDto.getEmail());
        if (usuarios == null) {
            return null;
        }

        boolean isValidPassword = passwordEncoder.matches(loginDto.getSenha(), usuarios.getSenha());
        if (!isValidPassword) {
            return null;
        }

        UsuariosDto usuariosDto = new UsuariosDto();
        usuariosDto.setEmail(usuarios.getEmail());
        usuariosDto.setTipoUsuario(usuarios.getTipoUsuario());
        usuariosDto.setNome(usuarios.getNome());
        usuariosDto.setCpf(usuarios.getCpf());

        return usuariosDto;
    }

    public Boolean existsByEmail(String email) {
        return usuariosRepository.existsByEmail(email);
    }

    public Boolean existsBycpf(String cpf) {
        return usuariosRepository.existsByCpf(cpf);
    }

    public boolean cpfValidator(String cpf) {
        boolean cpfIsValid = ValidarCPF.isCPF(cpf);
        return cpfIsValid;
    }

    public boolean validarToken(String token) {
        return authFeign.validarToken(token);
    }

    public String getTypeUser(String token) {
        return authFeign.getTypeUser(token);
    }


}
