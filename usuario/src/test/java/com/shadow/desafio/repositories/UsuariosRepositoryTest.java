package com.shadow.desafio.repositories;

import org.junit.jupiter.api.BeforeEach;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
@RunWith(SpringRunner.class)
@SpringBootTest
class UsuariosRepositoryTest {
    @Autowired
    private UsuariosRepository usuariosRepository;

    @BeforeEach
    void setUp() {
    }

}