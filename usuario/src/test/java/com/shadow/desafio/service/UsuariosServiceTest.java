package com.shadow.desafio.service;

import com.shadow.desafio.entities.Usuarios;
import com.shadow.desafio.repositories.UsuariosRepository;
import com.shadow.desafio.service.exceptions.UserNotFoundException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.web.WebAppConfiguration;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;


@SpringBootTest
@ExtendWith(SpringExtension.class)
@WebAppConfiguration
@AutoConfigureMockMvc
class UsuariosServiceTest {

    public static final String ID = "1";
    public static final String NOME = "Maycon";
    public static final String CPF = "51572343257";
    public static final String EMAIL = "maycon@gmail.com";
    public static final String SENHA = "123456";
    public static final String CLIENTE = "ADMIN";

    @InjectMocks
    private UsuariosService usuariosService;
    @Mock
    private UsuariosRepository usuariosRepository;


    private Usuarios usuarios;
    private Optional<Usuarios> optionalUsuarios;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this.usuariosService ); // Iniciar os Mocks.
        startUser();
    }

    @Test
    @DisplayName("Deve retornar um usuario ao buscar por ID")
    void  whenFindByIdThenReturnUserInstance() {
        // Optional<Usuarios> usuarios = usuariosRepository.findById(codigoID);
        Mockito.when(usuariosRepository.findById((Mockito.anyString())))
                .thenReturn(optionalUsuarios);

        Usuarios response = usuariosService.findById(ID);

        assertNotNull(response); // Assegura que o response não seja null.

        assertNotNull(usuarios);

        assertEquals(Usuarios.class, response.getClass());

        assertEquals(ID, response.getCodigoID());

    }

    @Test
    @DisplayName("Quando id não encontrado ou não existe deve retorna uma resposta")
    void whenFindByIdThenReturnAnObjectNotFoundExcepton() {
        // Quando chamar findByID chama essa mensagem da exception
        Mockito.when(usuariosRepository.findById(Mockito.anyString()))
                        .thenThrow( new UserNotFoundException());

        try {
            usuariosService.findById(ID);
        } catch (Exception e) {
            assertEquals(UserNotFoundException.class, e.getClass());
            assertEquals("Usuário não encontrado", e.getMessage());

        }
    }

    @Test
    @DisplayName("Deve retornar a lista de todos os usuarios")
    void whenFindallThenReturnAnListUsers() {


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

    private void startUser() {
        usuarios = new Usuarios(ID, NOME, CPF, EMAIL, SENHA, CLIENTE);
        optionalUsuarios = Optional.of(new Usuarios(ID, NOME, CPF, EMAIL, SENHA, CLIENTE));
    }
}