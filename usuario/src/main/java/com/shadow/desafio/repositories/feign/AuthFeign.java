package com.shadow.desafio.repositories.feign;

import org.springframework.cloud.openfeign.FeignClient;

@FeignClient(name = "feign", url = "")
public interface AuthFeign {

/*
    @GetMapping(value = "/auth/validatetoken")
    boolean validateToken(@RequestHeader(HttpHeaders.AUTHORIZATION)String token);

    @GetMapping(value = "/auth/typeuser")
    String getTypeUser(@RequestHeader(HttpHeaders.AUTHORIZATION)String token);
*/
}
