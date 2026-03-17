package com.logitrack.dto;

import lombok.*;

import java.time.LocalDateTime;
import java.util.List;

@Getter @Setter
@NoArgsConstructor @AllArgsConstructor
@Builder
public class MovimientoResponse {

    private Long id;
    private LocalDateTime fecha;
    private String tipoMovimiento;
    private String usuario;
    private String bodegaOrigen;
    private String bodegaDestino;
    private List<DetalleResponse> detalles;

    @Getter @Setter
    @NoArgsConstructor @AllArgsConstructor
    @Builder
    public static class DetalleResponse {
        private String producto;
        private Integer cantidad;
    }
}
