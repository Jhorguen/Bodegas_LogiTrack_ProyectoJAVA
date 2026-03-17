package com.logitrack.dto;

import lombok.*;

import java.time.LocalDateTime;

@Getter @Setter
@NoArgsConstructor @AllArgsConstructor
@Builder
public class AuditoriaResponse {

    private Long id;
    private String tipoOperacion;
    private LocalDateTime fechaHora;
    private String usuario;
    private String entidadAfectada;
    private String valoresAnteriores;
    private String valoresNuevos;
}
