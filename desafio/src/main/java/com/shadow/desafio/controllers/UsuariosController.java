package com.shadow.desafio.controllers;

import com.shadow.desafio.dtos.UsuariosDto;
import com.shadow.desafio.entities.Usuarios;
import com.shadow.desafio.services.UsuariosService;
import jakarta.validation.Valid;
import org.springframework.beans.BeanUtils;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.time.ZoneId;

@RestController
@CrossOrigin(origins = "*", maxAge = 3600)/* Permitir acesso a qualquer fonte*/
@RequestMapping("/usuarios")
public class UsuariosController {

    final UsuariosService usuariosService;
    public UsuariosController(UsuariosService usuariosService) {
        this.usuariosService = usuariosService;
    }


    @PostMapping
    @RequestMapping("/salvar")
    public ResponseEntity<Object> salvarUsuarios(@RequestBody @Valid UsuariosDto usuariosDto){
        var usuarios = new Usuarios();/*Obs: Não tem no java 8*/
        BeanUtils.copyProperties(usuariosDto, usuarios); /*Conversão de DTO para Entity ates de salvar*/
        return ResponseEntity.status(HttpStatus.CREATED).body(usuariosService.save(usuarios));
    }


}
