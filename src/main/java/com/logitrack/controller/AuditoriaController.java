package com.logitrack.controller;

import com.logitrack.dto.AuditoriaResponse;
import com.logitrack.service.AuditoriaService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/auditorias")
@RequiredArgsConstructor
public class AuditoriaController {

    private final AuditoriaService auditoriaService;

    @GetMapping
    public ResponseEntity<List<AuditoriaResponse>> listarTodas() {
        return ResponseEntity.ok(auditoriaService.listarTodas());
    }

    @GetMapping("/por-usuario")
    public ResponseEntity<List<AuditoriaResponse>> listarPorUsuario(@RequestParam String usuario) {
        return ResponseEntity.ok(auditoriaService.listarPorUsuario(usuario));
    }

    @GetMapping("/por-tipo")
    public ResponseEntity<List<AuditoriaResponse>> listarPorTipo(@RequestParam String tipo) {
        return ResponseEntity.ok(auditoriaService.listarPorTipoOperacion(tipo));
    }
}
