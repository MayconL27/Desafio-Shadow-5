package com.shadow.desafio;

import com.shadow.desafio.util.ValidarCPF;
import org.junit.runner.RunWith;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;

@SpringBootTest
@AutoConfigureMockMvc
@RunWith(SpringRunner.class)
@WebAppConfiguration
public class TestarCPF extends AplicationConfigTest{
    public static void main(String[] args) {


        boolean idCpf = ValidarCPF.isCPF("50818326697");
        System.out.println("CPF Válido: " + idCpf);

    }
}
