package com.logitrack.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class BodegaRequest {

    @NotBlank
    @Size(min = 2, max = 100)
    private String nombre;

    @NotBlank
    @Size(max = 200)
    private String ubicacion;

    @Min(1)
    private Integer capacidad;

    @NotBlank
    @Size(max = 100)
    private String encargado;
}
