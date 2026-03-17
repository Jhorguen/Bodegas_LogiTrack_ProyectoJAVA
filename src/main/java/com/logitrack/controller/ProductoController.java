package com.logitrack.controller;

import com.logitrack.dto.ProductoRequest;
import com.logitrack.dto.ProductoResponse;
import com.logitrack.service.ProductoService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/productos")
@RequiredArgsConstructor
public class ProductoController {

    private final ProductoService productoService;

    @GetMapping
    public ResponseEntity<List<ProductoResponse>> listarTodos() {
        return ResponseEntity.ok(productoService.listarTodos());
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProductoResponse> obtenerPorId(@PathVariable Long id) {
        return ResponseEntity.ok(productoService.obtenerPorId(id));
    }

    @GetMapping("/stock-bajo")
    public ResponseEntity<List<ProductoResponse>> obtenerStockBajo() {
        return ResponseEntity.ok(productoService.obtenerStockBajo());
    }

    @PostMapping
    public ResponseEntity<ProductoResponse> crear(@Valid @RequestBody ProductoRequest request,
                                                   Authentication auth) {
        return ResponseEntity.ok(productoService.crear(request, auth.getName()));
    }

    @PutMapping("/{id}")
    public ResponseEntity<ProductoResponse> actualizar(@PathVariable Long id,
                                                        @Valid @RequestBody ProductoRequest request,
                                                        Authentication auth) {
        return ResponseEntity.ok(productoService.actualizar(id, request, auth.getName()));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Long id, Authentication auth) {
        productoService.eliminar(id, auth.getName());
        return ResponseEntity.noContent().build();
    }
}
