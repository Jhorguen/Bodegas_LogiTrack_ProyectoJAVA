package com.logitrack.controller;

import com.logitrack.dto.ReporteMovimientosResponse;
import com.logitrack.model.enums.TipoMovimiento;
import com.logitrack.repository.MovimientoInventarioRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.LinkedHashMap;
import java.util.Map;

@RestController
@RequestMapping("/reportes")
@RequiredArgsConstructor
public class ReporteController {

    private final MovimientoInventarioRepository movimientoRepository;

    @GetMapping("/movimientos")
    public ResponseEntity<ReporteMovimientosResponse> reporteMovimientos() {
        long total = movimientoRepository.count();

        Map<String, Long> porTipo = new LinkedHashMap<>();
        for (TipoMovimiento tipo : TipoMovimiento.values()) {
            porTipo.put(tipo.name(), movimientoRepository.countByTipoMovimiento(tipo));
        }

        return ResponseEntity.ok(ReporteMovimientosResponse.builder()
                .totalMovimientos(total)
                .movimientosPorTipo(porTipo)
                .build());
    }
}
