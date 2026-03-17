package com.logitrack.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.*;

@Entity
@Table(name = "bodegas")
@Getter @Setter
@NoArgsConstructor @AllArgsConstructor
@Builder
public class Bodega {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    @Size(min = 2, max = 100)
    @Column(nullable = false)
    private String nombre;

    @NotBlank
    @Size(max = 200)
    @Column(nullable = false)
    private String ubicacion;

    @Min(1)
    @Column(nullable = false)
    private Integer capacidad;

    @NotBlank
    @Size(max = 100)
    @Column(nullable = false)
    private String encargado;
}
