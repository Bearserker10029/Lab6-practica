package com.example.msestadisticas;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

// El name debe coincidir EXACTO con spring.application.name del servicio en Eureka
@FeignClient(name = "ms-sesiones")
public interface SesionClient {

    @GetMapping("/sesiones")
    List<Sesion> listarSesiones();
}