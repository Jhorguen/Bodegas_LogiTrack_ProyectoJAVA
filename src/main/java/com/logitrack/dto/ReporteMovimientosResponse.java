package com.logitrack.dto;

import lombok.*;

import java.util.Map;

@Getter @Setter @NoArgsConstructor @AllArgsConstructor @Builder
public class ReporteMovimientosResponse {

    private long totalMovimientos;
    private Map<String, Long> movimientosPorTipo;
}
