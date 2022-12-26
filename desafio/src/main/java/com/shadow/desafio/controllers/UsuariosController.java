package com.shadow.desafio.controllers;

import com.shadow.desafio.entities.Usuarios;
import com.shadow.desafio.repositories.UsuariosRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@CrossOrigin(origins = "*", maxAge = 3600)/* Permitir acesso a qualquer fonte*/
public class UsuariosController {

    private final UsuariosRepository usuariosRepository;
    private final PasswordEncoder encoder;

    public UsuariosController( UsuariosRepository usuariosRepository, PasswordEncoder encoder) {
        this.usuariosRepository = usuariosRepository;
        this.encoder = encoder;
    }
    @PostMapping(value = "salvar")
    public ResponseEntity<Usuarios> salvarUsuarios2(@RequestBody Usuarios usuarios){
        Usuarios user = usuariosRepository.save(usuarios);
        usuarios.setSenha(encoder.encode(usuarios.getSenha())); /* BCrypt Senha encripitada */
        return new ResponseEntity<>(user,HttpStatus.CREATED);
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
    public ResponseEntity<?> atualizar(@RequestBody Usuarios usuarios) { /* <?> Pode restotar qualquer coisa */
        if (usuarios.getCodigoID() == null) {
            return new ResponseEntity<String>("Id não foi informado", HttpStatus.OK);
        }
        Usuarios user = usuariosRepository.saveAndFlush(usuarios);
        return new ResponseEntity<Usuarios>(user, HttpStatus.OK);
    }
}
