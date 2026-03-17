package com.logitrack.dto;

import lombok.*;

@Getter @Setter
@NoArgsConstructor @AllArgsConstructor
@Builder
public class BodegaResponse {

    private Long id;
    private String nombre;
    private String ubicacion;
    private Integer capacidad;
    private String encargado;
}
