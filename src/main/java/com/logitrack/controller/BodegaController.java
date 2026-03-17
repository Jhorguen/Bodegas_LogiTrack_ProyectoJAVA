package com.logitrack.controller;

import com.logitrack.dto.BodegaRequest;
import com.logitrack.dto.BodegaResponse;
import com.logitrack.service.BodegaService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/bodegas")
@RequiredArgsConstructor
public class BodegaController {

    private final BodegaService bodegaService;

    @GetMapping
    public ResponseEntity<List<BodegaResponse>> listarTodas() {
        return ResponseEntity.ok(bodegaService.listarTodas());
    }

    @GetMapping("/{id}")
    public ResponseEntity<BodegaResponse> obtenerPorId(@PathVariable Long id) {
        return ResponseEntity.ok(bodegaService.obtenerPorId(id));
    }

    @PostMapping
    public ResponseEntity<BodegaResponse> crear(@Valid @RequestBody BodegaRequest request,
                                                 Authentication auth) {
        return ResponseEntity.ok(bodegaService.crear(request, auth.getName()));
    }

    @PutMapping("/{id}")
    public ResponseEntity<BodegaResponse> actualizar(@PathVariable Long id,
                                                      @Valid @RequestBody BodegaRequest request,
                                                      Authentication auth) {
        return ResponseEntity.ok(bodegaService.actualizar(id, request, auth.getName()));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Long id, Authentication auth) {
        bodegaService.eliminar(id, auth.getName());
        return ResponseEntity.noContent().build();
    }
}
