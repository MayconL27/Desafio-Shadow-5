package com.shadow.desafio.controllers;

import com.shadow.desafio.AplicationConfigTest;
import com.shadow.desafio.entities.Usuarios;
import com.shadow.desafio.service.UsuariosService;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.module.mockmvc.RestAssuredMockMvc;
import io.restassured.response.Response;
import org.junit.Assert;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.web.bind.annotation.RequestHeader;

import static org.hamcrest.Matchers.equalTo;
import static org.mockito.Mockito.mock;
@AutoConfigureMockMvc
@ContextConfiguration
public class UsuariosControllerTest extends AplicationConfigTest {
    @Autowired
    private MockMvc mockMvc;

    @InjectMocks
    private UsuariosController usuariosController;
    @MockBean
    private UsuariosService usuariosService;
    Usuarios usuario = mock(Usuarios.class);

    String token = "eyJhbGciOiJIUzI1NiJ9.eyJuYW1lIjoiTWF5Y29uIiwiY3BmIjoiMTg3ODc0NzcyMDMiLCJ0aXBvVXN1YXJpbyI6IkFETUlOIiwiZXhwIjoxNjc1ODgwNjEyLCJpYXQiOjE2NzU4ODA1NTIsImVtYWlsIjoiTWF5Y29uQGdtYWlsLmNvbSJ9.6QBH2BcODlWUoZMEPlC7G1x_MV5p6s9kL6jFJr6Ctpo";

    @BeforeEach // antes e qualquer metodo executa isso.
    public void setup() {
        RestAssuredMockMvc.mockMvc(this.mockMvc);
    }

    @Test
    public void test_1() {
        // java.net.ConnectException: Connection refused: connect
        Response response = RestAssured.get("https://reqres.in/api/users?page=2");
        System.out.println(response.getStatusCode());
        System.out.println(response.getTime());
        System.out.println(response.getBody().asString());
        System.out.println(response.getStatusLine());
        System.out.println(response.getHeader("content-type"));

        int statusCode = response.getStatusCode();
        Assert.assertEquals(statusCode,200);
    }

    @Test
    @DisplayName("Dado uma requisição sem autorização, deve retornar 401 e mensagem informativa!")
    public void testGetListAllUsers() throws Exception {
        RestAssuredMockMvc.given()
                .contentType(ContentType.JSON)
                .header( "AUTHORIZATION",token)
                .queryParam("", "")
                .when()
                .get("/usuario/listartodos/")
                .then()
                .statusCode(401)
                .body("mensagem", equalTo("Usuario não autorizado"));
    }

//    public String gerarToken(@RequestHeader String token) {
//
//    }
    @Test
    public void testGetUserID() throws Exception {
        RestAssuredMockMvc.given()
                .contentType(ContentType.JSON)
                .header("","")
                .queryParam("", "")
                .when()
                .get("/usuario/listartodos/{parametro}", "1234")
                .then()
                .statusCode(200)
                .body("mensagem", equalTo("Usuario não autorizado"));
    }




//    @Test
//    public void testSaveUsuarioController() throws Exception {
//        this.mockMvc.perform(MockMvcRequestBuilders.get("/usuario/salvar"))
//                .andExpect(MockMvcResultMatchers.model().attributeExists("Maycon"))
//                .andExpect(MockMvcResultMatchers.status().isOk());
//    }
//
//    @Test
//    public void testSalvarUsuario() throws Exception {
//        this.mockMvc.perform(MockMvcRequestBuilders.get("/usuario/salvar")
//                .param("codigoID", "1")
//                .param("nome", "Maycon")
//                .param("cpf", "12345678901")
//                .param("email", "maycon@gmail.com")
//                .param("senha", "123")
//                .param("tipoUsuario", "ADMIN"))
//                .andExpect(MockMvcResultMatchers.redirectedUrl("/usuario/salvar"));
//    }

}