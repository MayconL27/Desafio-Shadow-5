package com.shadow.desafio.repositories.feign;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.http.HttpHeaders;

@FeignClient(name = "auth", url = "http://localhost:8081") // port Desafio 7
public interface AuthFeign {
    @GetMapping(value = "/auth/validartoken")
    boolean validarToken(@RequestHeader(HttpHeaders.AUTHORIZATION)String token);

    @GetMapping(value = "/auth/tipousuario")
    String getTipoUsuario(@RequestHeader(HttpHeaders.AUTHORIZATION)String token);


}
