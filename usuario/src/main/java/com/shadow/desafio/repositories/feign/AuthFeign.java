package com.shadow.desafio.repositories.feign;

import org.springframework.cloud.openfeign.FeignClient;

@FeignClient(name = "feign", url = "")
public interface AuthFeign {

}
