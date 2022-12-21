package com.shadow.desafio.controllers;

import com.shadow.desafio.dtos.UsuariosDto;
import com.shadow.desafio.entities.Usuarios;
import com.shadow.desafio.repositories.UsuariosRepository;
import com.shadow.desafio.services.UsuariosService;
import jakarta.validation.Valid;
import org.springframework.beans.BeanUtils;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.List;

@RestController
@CrossOrigin(origins = "*", maxAge = 3600)/* Permitir acesso a qualquer fonte*/
@RequestMapping("/usuarios")
public class UsuariosController {

    final UsuariosService usuariosService;
    private final UsuariosRepository usuariosRepository;

    public UsuariosController(UsuariosService usuariosService,
                              UsuariosRepository usuariosRepository) {
        this.usuariosService = usuariosService;
        this.usuariosRepository = usuariosRepository;
    }

    @PostMapping
    public ResponseEntity<Object> salvarUsuarios(@RequestBody @Valid UsuariosDto usuariosDto){
        var usuarios = new Usuarios();/*Obs: Não tem no java 8*/
        BeanUtils.copyProperties(usuariosDto, usuarios); /*Conversão de DTO para Entity ates de salvar*/
        return ResponseEntity.status(HttpStatus.CREATED).body(usuariosService.save(usuarios));
    }
/*
    @GetMapping
    public ResponseEntity<List<Usuarios>> listarTodosUsuarios(){
        return ResponseEntity.status(HttpStatus.OK).body(UsuariosService.findAll());
    }*/

    @GetMapping
    public ResponseEntity<List<Usuarios>> listarTodosUsuarios(){
        List<Usuarios> usuarios = usuariosRepository.findAll();
        return new ResponseEntity<List<Usuarios>>(usuarios, HttpStatus.OK);
    }



}
