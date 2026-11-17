package com.example.mssesiones;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicLong;

@RestController
@RequestMapping("/sesiones")
public class SesionController {

    private final List<Sesion> sesiones = new ArrayList<>();
    private final AtomicLong contadorId = new AtomicLong(0);

    public SesionController() {
        sesiones.add(new Sesion(contadorId.incrementAndGet(), "Servicios y Aplicaciones para IoT", "28/10/2026", 2, "Laboratorio"));
        sesiones.add(new Sesion(contadorId.incrementAndGet(), "Redes de Computadoras", "27/10/2026", 1.5, "Teoria"));
        sesiones.add(new Sesion(contadorId.incrementAndGet(), "Comunicaciones Digitales", "25/10/2026", 3, "Practica"));
        sesiones.add(new Sesion(contadorId.incrementAndGet(), "Servicios y Aplicaciones para IoT", "22/10/2026", 1, "Teoria"));
    }

    @GetMapping
    public List<Sesion> listar() {
        return sesiones;
    }

    @GetMapping("/{id}")
    public ResponseEntity<Sesion> obtener(@PathVariable Long id) {
        return sesiones.stream()
                .filter(s -> s.getId().equals(id))
                .findFirst()
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public Sesion registrar(@RequestBody Sesion sesion) {
        sesion.setId(contadorId.incrementAndGet());
        sesiones.add(sesion);
        return sesion;
    }

    @PutMapping("/{id}")
    public ResponseEntity<Sesion> actualizar(@PathVariable Long id, @RequestBody Sesion datos) {
        for (Sesion s : sesiones) {
            if (s.getId().equals(id)) {
                s.setCurso(datos.getCurso());
                s.setFecha(datos.getFecha());
                s.setHoras(datos.getHoras());
                s.setTipo(datos.getTipo());
                return ResponseEntity.ok(s);
            }
        }
        return ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Long id) {
        boolean removido = sesiones.removeIf(s -> s.getId().equals(id));
        return removido ? ResponseEntity.noContent().build() : ResponseEntity.notFound().build();
    }
}