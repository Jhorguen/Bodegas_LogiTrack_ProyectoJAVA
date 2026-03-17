package com.logitrack.dto;

import com.logitrack.model.enums.TipoMovimiento;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter @Setter
public class MovimientoRequest {

    @NotNull
    private TipoMovimiento tipoMovimiento;

    private Long bodegaOrigenId;

    private Long bodegaDestinoId;

    @NotNull
    private List<MovimientoDetalleRequest> detalles;
}
