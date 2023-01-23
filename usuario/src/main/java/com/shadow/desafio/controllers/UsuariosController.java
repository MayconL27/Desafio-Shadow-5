package com.shadow.desafio.controllers;

import com.shadow.desafio.dtos.LoginDto;
import com.shadow.desafio.dtos.UsuariosDto;
import com.shadow.desafio.entities.Usuarios;
import com.shadow.desafio.service.UsuariosService;
import com.shadow.desafio.service.exceptions.MessageExceptionHandler;
import org.springframework.beans.BeanUtils;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Date;
import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping("/usuario")
@CrossOrigin(origins = "*", maxAge = 3600)/* Permitir acesso a qualquer fonte */
public class UsuariosController {

    final UsuariosService usuariosService;
    public UsuariosController(UsuariosService usuariosService) {
        this.usuariosService = usuariosService;
    }

    @PostMapping(value = "/salvar") // Salvar usuário.
    public ResponseEntity<Object> salvarUsuarios(@RequestBody Usuarios usuarios,
                                                 @RequestHeader(HttpHeaders.AUTHORIZATION)String token) {
        if (!usuariosService.validarToken(token)) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(new MessageExceptionHandler(new Date(), HttpStatus.UNAUTHORIZED.value(), "Token Inválido" ));
        }
//        if (!usuariosService.getTypeUser(token).equals("ADMIN")){
//            return ResponseEntity.status(HttpStatus.FORBIDDEN).body(new MessageExceptionHandler(new Date(), HttpStatus.FORBIDDEN.value(), "Usuário sem permisão" ));
//        }

        if (usuariosService.existsByEmail(usuarios.getEmail())){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new MessageExceptionHandler(new Date(), HttpStatus.NOT_FOUND.value(),"E-mail já cadastrado" ));
        }

        if (usuariosService.existsBycpf(usuarios.getCpf())){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new MessageExceptionHandler(new Date(), HttpStatus.NOT_FOUND.value(),"CPF já cadastrado" ));
        }

        if (!usuariosService.cpfValidator(usuarios.getCpf())) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new MessageExceptionHandler(new Date(), HttpStatus.NOT_FOUND.value(),"CPF invalido" ));
        }
        return ResponseEntity.status(HttpStatus.CREATED).body(usuariosService.save(usuarios));
    }
    @GetMapping(value = "/listartodos") // Listar todos os usuários.
    public ResponseEntity<Object> listarTodos(@RequestHeader(HttpHeaders.AUTHORIZATION)String token){

        if (!usuariosService.validarToken(token)) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(new MessageExceptionHandler(new Date(), HttpStatus.UNAUTHORIZED.value(), "Token Inválido" ));
        }
//        if (!usuariosService.getTypeUser(token).equals("ADMIN")){
//            return ResponseEntity.status(HttpStatus.FORBIDDEN).body(new MessageExceptionHandler(new Date(), HttpStatus.FORBIDDEN.value(), "Usuário sem permisão" ));
//        }


        return ResponseEntity.status(HttpStatus.OK).body(usuariosService.findAll());
    }
    @GetMapping(value = "/{codigoID}") // Buscar por Id.
    public ResponseEntity<Object> buscarID(@PathVariable UUID codigoID,
                                            @RequestHeader(HttpHeaders.AUTHORIZATION)String token) {
         return new ResponseEntity<>(usuariosService.findById(codigoID), HttpStatus.OK);
    }
    @DeleteMapping("/{codigoID}") // Delete por ID.
    public ResponseEntity<Object> delete(@PathVariable(value = "codigoID") UUID codigoID,
                                         @RequestHeader(HttpHeaders.AUTHORIZATION)String token){
        Usuarios usuarios = usuariosService.findById(codigoID);
        usuariosService.delete(usuarios);
        return ResponseEntity.status(HttpStatus.OK).body(new MessageExceptionHandler(new Date(),HttpStatus.OK.value(),"Usuário deletado"));
    }
    @PutMapping("/{id}")
    public ResponseEntity<Object> atualizar(@PathVariable(value = "id") UUID codigoID,
                                            @RequestBody @Valid UsuariosDto usuariosDto,
                                            @RequestHeader(HttpHeaders.AUTHORIZATION)String token){
        Optional<Usuarios> usuariosOptional = Optional.ofNullable(usuariosService.findById(codigoID));
        if (!usuariosOptional.isPresent()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Usuário não encontrado.");
        }
        var usuarios = new Usuarios();
        BeanUtils.copyProperties(usuariosDto, usuarios); /* conversão de Dto para entity*/
        usuarios.setCodigoID(usuariosOptional.get().getCodigoID()); /* Setando Id para permanecer o mesmo */
        return ResponseEntity.status(HttpStatus.OK).body(usuariosService.save(usuarios));
    }


    @PostMapping("/login")
    public ResponseEntity<Object> loginUser(@RequestBody LoginDto loginDto) {
        Object response = usuariosService.loginUsuario(loginDto);
        if (response == null) {
            MessageExceptionHandler error = new MessageExceptionHandler(new Date(), HttpStatus.NOT_FOUND.value(), "E-mail ou senha inválido.");
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(error);
        }
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }
}
