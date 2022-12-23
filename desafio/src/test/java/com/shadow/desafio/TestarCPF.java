package com.shadow.desafio;

import com.shadow.desafio.util.ValidarCPF;

public class TestarCPF {
    public static void main(String[] args) {

        boolean idCpf = ValidarCPF.isCPF("27378737208");
        System.out.println("CPF Válido: " + idCpf);

    }
}
