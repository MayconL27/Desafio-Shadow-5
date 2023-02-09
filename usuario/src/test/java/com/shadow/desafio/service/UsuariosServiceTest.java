package com.shadow.desafio.service;

import com.shadow.desafio.dtos.LoginDto;
import com.shadow.desafio.entities.Usuarios;
import com.shadow.desafio.repositories.UsuariosRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Optional;
import java.util.UUID;


@SpringBootTest
class UsuariosServiceTest {

    public static final String ID = "1";
    public static final String NAME = "Maycon";
    public static final String CPF = "51572343257";
    public static final String EMAIL = "maycon@gmail.com";
    public static final String SENHA = "123456";
    public static final String TIPOCLIENTE = "ADMIN";
    @InjectMocks
    private UsuariosService usuariosService;
    @Mock
    UsuariosRepository usuariosRepository;

    private Usuarios usuarios;
    private Optional<Usuarios> optionalUsuarios;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this.usuariosService );

        usuarios = new Usuarios("1","Maycon","51572343257","maycon@gmail.com","123456","ADMIN");
        optionalUsuarios = Optional.of(new Usuarios("1","Maycon","51572343257","maycon@gmail.com","123456","ADMIN"));

    }

    @Test
    @DisplayName("Deve retornar um usuario ao buscar por ID")
    void whenFindByIdThenReturnUserInstance() {
        // Optional<Usuarios> usuarios = usuariosRepository.findById(codigoID);
        Mockito.when(usuariosRepository.findById((Mockito.anyString()))).thenReturn(optionalUsuarios);

        Usuarios response = usuariosService.findById("1");

        Assertions.assertEquals(Usuarios.class, response.getClass());

    }

    @Test
    @DisplayName("Deve retornar a lista de todos os usuarios")
    void whenFindallThenReturnAnListUsers() {


    }

    @Test
    @DisplayName("Login de usuario deve retornar ok")
    void loginUsuario() {

        //setup
        //Arrange
        LoginDto loginDto = new LoginDto();
        loginDto.getEmail();
        loginDto.getSenha();

//        Mockito.when(usuariosService.loginUsuario()).thenReturn(Optional.of(usuariosRepository))

        //teste
        //Action
        usuariosService.loginUsuario(loginDto);

        //validacoes
        //Assertions

    }

    @Test
    void existsByEmail() {
    }

    @Test
    void existsBycpf() {
    }

    @Test
    void cpfValidator() {
    }

    @Test
    void validarToken() {
    }
}