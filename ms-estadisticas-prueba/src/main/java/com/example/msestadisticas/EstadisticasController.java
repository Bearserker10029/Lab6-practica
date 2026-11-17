package com.example.msestadisticas;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

@RestController
public class EstadisticasController {

    private final SesionClient sesionClient;

    public EstadisticasController(SesionClient sesionClient) {
        this.sesionClient = sesionClient;
    }

    // Equivalente al grafico de barras del Lab 6
    @GetMapping("/estadisticas/horasPorMes")
    public Map<String, Double> horasPorMes() {
        List<Sesion> sesiones = sesionClient.listarSesiones();
        Map<String, Double> resultado = new LinkedHashMap<>();
        for (Sesion s : sesiones) {
            String mes = s.getFecha().substring(3); // mm/yyyy desde dd/MM/yyyy
            resultado.merge(mes, s.getHoras(), Double::sum);
        }
        return resultado;
    }

    // Equivalente al grafico de torta del Lab 6
    @GetMapping("/estadisticas/horasPorTipo")
    public Map<String, Double> horasPorTipo() {
        List<Sesion> sesiones = sesionClient.listarSesiones();
        Map<String, Double> resultado = new LinkedHashMap<>();
        for (Sesion s : sesiones) {
            resultado.merge(s.getTipo(), s.getHoras(), Double::sum);
        }
        return resultado;
    }

    @GetMapping("/estadisticas/resumen")
    public Map<String, Object> resumen() {
        Map<String, Object> resumen = new LinkedHashMap<>();
        resumen.put("sesionesRegistradas", sesionClient.listarSesiones().size());
        resumen.put("horasPorMes", horasPorMes());
        resumen.put("horasPorTipo", horasPorTipo());
        return resumen;
    }
}