package com.shadow.desafio.controllers;

import com.shadow.desafio.dtos.UsuariosDto;
import com.shadow.desafio.entities.Usuarios;
import com.shadow.desafio.exceptions.Exceptions;
import com.shadow.desafio.repositories.UsuariosRepository;
import com.shadow.desafio.services.UsuariosService;
import jakarta.validation.Valid;
import org.springframework.beans.BeanUtils;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@CrossOrigin(origins = "*", maxAge = 3600)/* Permitir acesso a qualquer fonte*/
public class UsuariosController {

    final UsuariosService usuariosService;
    private final UsuariosRepository usuariosRepository;
    private final PasswordEncoder encoder;

    public UsuariosController(UsuariosService usuariosService, UsuariosRepository usuariosRepository, PasswordEncoder encoder) {
        this.usuariosService = usuariosService;
        this.usuariosRepository = usuariosRepository;
        this.encoder = encoder;
    }

    @PostMapping(value = "salvar")
    public ResponseEntity<Object> salvarUsuarios(@RequestBody @Valid UsuariosDto usuariosDto){
        var usuarios = new Usuarios();/*Obs: Não tem no java 8*/
        BeanUtils.copyProperties(usuariosDto, usuarios); /*Conversão de DTO para Entity ates de salvar*/
        usuarios.setSenha(encoder.encode(usuarios.getSenha())); /* BCrypt Senha encripitada */
        return ResponseEntity.status(HttpStatus.CREATED).body(usuariosService.save(usuarios));
    }

    @GetMapping(value = "listartodos")
    public ResponseEntity<List<Usuarios>> ProcurarID(){
        List<Usuarios> usuarios = usuariosRepository.findAll();
        return new ResponseEntity<List<Usuarios>>(usuarios, HttpStatus.OK);
    }
    @GetMapping(value = "buscarid")
    public ResponseEntity<Usuarios> buscarID(@RequestParam(name = "codigoid") UUID CodigoID) {
        Usuarios usuarios = usuariosRepository.findById(CodigoID).get();
        return new ResponseEntity<Usuarios>(usuarios, HttpStatus.OK);
    }

    @DeleteMapping(value = "delete")
    public ResponseEntity<String> deleteUsuario(@RequestBody Usuarios usuarios) {
        usuariosRepository.delete(usuarios);
        return new ResponseEntity<String>("Usuário deletado com sucesso", HttpStatus.OK);
    }
    @PutMapping(value = "atualizar")
    public Usuarios atualizarUsuario(@RequestBody Usuarios usuarios) {
        return usuariosRepository.save(usuarios);
    }


}
