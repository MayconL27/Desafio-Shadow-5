package com.shadow.desafio.controllers;

import com.shadow.desafio.entities.Usuarios;
import com.shadow.desafio.repositories.UsuariosRepository;
import com.shadow.desafio.resources.exceptions.StandarError;
import com.shadow.desafio.service.UsuariosService;
import com.shadow.desafio.service.exceptions.EntityNotFoundException;
import com.shadow.desafio.util.ValidarCPF;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.time.Instant;
import java.util.List;
import java.util.UUID;


@RestController
@CrossOrigin(origins = "*", maxAge = 3600)/* Permitir acesso a qualquer fonte*/
@Data
public class UsuariosController {

    @Autowired
    private UsuariosService usuariosService;
    @Autowired
    private UsuariosRepository usuariosRepository;
    private final PasswordEncoder encoder;

    @PostMapping(value = "salvar")
    public ResponseEntity<?> salvarUsuarios(@RequestBody Usuarios usuarios){
        if (!ValidarCPF.isCPF(usuarios.getCpf())) { /* Se retornar CPf falso: */
            //throw new Exception("Cpf: " + usuarios.getCpf() + " está inválido.");
            return new ResponseEntity<String>("Cpf " + usuarios.getCpf() +" inválido", HttpStatus.OK);
        }
        Usuarios user = usuariosRepository.save(usuarios);
        usuarios.setSenha(encoder.encode(usuarios.getSenha())); /* BCrypt Senha encripitada */
        return new ResponseEntity<>(user,HttpStatus.CREATED);
    }
    @GetMapping(value = "listartodos")
    public ResponseEntity<List<Usuarios>> ProcurarID(){
        List<Usuarios> usuarios = usuariosRepository.findAll();
        return new ResponseEntity<List<Usuarios>>(usuarios, HttpStatus.OK);
    }
    @GetMapping(value = "buscarid") /* Com RequestParam */
    public ResponseEntity<Usuarios> buscarID(@RequestParam(name = "codigoid") UUID codigoID) {
        Usuarios usuarios = usuariosRepository.findById(codigoID).get();
        return new ResponseEntity<Usuarios>(usuarios, HttpStatus.OK);
    }
    @GetMapping(value = "/{codigoID}") /* Passando a Variavel */
    public ResponseEntity<Usuarios> buscarID2(@PathVariable UUID codigoID) {
            Usuarios usuarios = usuariosService.findById(codigoID);
            return new ResponseEntity<Usuarios>(usuarios, HttpStatus.OK);
    }

    @DeleteMapping(value = "delete")
    public ResponseEntity<String> deleteUsuario(@RequestBody Usuarios usuarios) {
        usuariosRepository.delete(usuarios);
        return new ResponseEntity<String>("Usuário deletado com sucesso", HttpStatus.OK);
    }
    @PutMapping(value = "atualizar")
    public ResponseEntity<?> atualizar(@RequestBody Usuarios usuarios) { /* <?> Pode restotar qualquer coisa */
        if (usuarios.getCodigoID() == null) {
            return new ResponseEntity<String>("Id não foi informado", HttpStatus.OK);
        }
        Usuarios user = usuariosRepository.saveAndFlush(usuarios);
        return new ResponseEntity<Usuarios>(user, HttpStatus.OK);
    }
}
