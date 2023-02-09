package com.shadow.desafio;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ExtendWith(SpringExtension.class)
@TestPropertySource(locations = "classpath:applicationTest.properties")
@SpringBootTest(classes = UsuarioApplication.class)
public class AplicationConfigTest {

    @Test
    @DisplayName("Teste do main")
    void main() {
        UsuarioApplication.main(new String[] {});
    }






}
