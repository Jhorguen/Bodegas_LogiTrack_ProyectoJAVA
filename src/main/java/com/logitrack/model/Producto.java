package com.logitrack.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.*;

@Entity
@Table(name = "productos")
@Getter @Setter
@NoArgsConstructor @AllArgsConstructor
@Builder
public class Producto {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    @Size(min = 2, max = 100)
    @Column(nullable = false)
    private String nombre;

    @NotBlank
    @Size(max = 50)
    @Column(nullable = false)
    private String categoria;

    @Min(0)
    @Column(nullable = false)
    private Integer stock;

    @Min(0)
    @Column(nullable = false)
    private Double precio;
}
