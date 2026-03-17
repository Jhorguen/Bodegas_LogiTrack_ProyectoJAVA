package com.logitrack.dto;

import lombok.*;

@Getter @Setter
@NoArgsConstructor @AllArgsConstructor
@Builder
public class ProductoResponse {

    private Long id;
    private String nombre;
    private String categoria;
    private Integer stock;
    private Double precio;
}
