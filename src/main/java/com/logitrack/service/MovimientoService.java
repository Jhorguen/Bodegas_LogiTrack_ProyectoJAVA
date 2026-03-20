package com.logitrack.service;

import com.logitrack.dto.MovimientoDetalleRequest;
import com.logitrack.dto.MovimientoRequest;
import com.logitrack.dto.MovimientoResponse;
import com.logitrack.exception.ResourceNotFoundException;
import com.logitrack.exception.StockInsuficienteException;
import com.logitrack.model.*;
import com.logitrack.model.enums.TipoMovimiento;
import com.logitrack.repository.*;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class MovimientoService {

    private final MovimientoInventarioRepository movimientoRepository;
    private final BodegaRepository bodegaRepository;
    private final ProductoRepository productoRepository;
    private final UsuarioRepository usuarioRepository;
    private final AuditoriaService auditoriaService;

    public List<MovimientoResponse> listarTodos() {
        return movimientoRepository.findAll().stream()
                .map(this::toResponse)
                .toList();
    }

    public List<MovimientoResponse> listarRecientes() {
        return movimientoRepository.findTop10ByOrderByFechaDesc().stream()
                .map(this::toResponse)
                .toList();
    }

    public List<MovimientoResponse> listarPorFechas(LocalDateTime inicio, LocalDateTime fin) {
        return movimientoRepository.findByFechaBetween(inicio, fin).stream()
                .map(this::toResponse)
                .toList();
    }

    @Transactional
    public MovimientoResponse crear(MovimientoRequest request, String username) {
        Usuario usuario = usuarioRepository.findByUsername(username)
                .orElseThrow(() -> new ResourceNotFoundException("Usuario no encontrado"));

        MovimientoInventario movimiento = new MovimientoInventario();
        movimiento.setTipoMovimiento(request.getTipoMovimiento());
        movimiento.setUsuario(usuario);

        // Validar bodegas según tipo de movimiento
        if (request.getTipoMovimiento() == TipoMovimiento.ENTRADA) {
            Bodega destino = bodegaRepository.findById(request.getBodegaDestinoId())
                    .orElseThrow(() -> new ResourceNotFoundException("Bodega destino no encontrada"));
            movimiento.setBodegaDestino(destino);

        } else if (request.getTipoMovimiento() == TipoMovimiento.SALIDA) {
            Bodega origen = bodegaRepository.findById(request.getBodegaOrigenId())
                    .orElseThrow(() -> new ResourceNotFoundException("Bodega origen no encontrada"));
            movimiento.setBodegaOrigen(origen);

        } else if (request.getTipoMovimiento() == TipoMovimiento.TRANSFERENCIA) {
            Bodega origen = bodegaRepository.findById(request.getBodegaOrigenId())
                    .orElseThrow(() -> new ResourceNotFoundException("Bodega origen no encontrada"));
            Bodega destino = bodegaRepository.findById(request.getBodegaDestinoId())
                    .orElseThrow(() -> new ResourceNotFoundException("Bodega destino no encontrada"));
            movimiento.setBodegaOrigen(origen);
            movimiento.setBodegaDestino(destino);
        }

        // Procesar detalles (productos y cantidades)
        for (MovimientoDetalleRequest detalleReq : request.getDetalles()) {
            Producto producto = productoRepository.findById(detalleReq.getProductoId())
                    .orElseThrow(() -> new ResourceNotFoundException("Producto no encontrado con id: " + detalleReq.getProductoId()));

            // Actualizar stock según tipo
            if (request.getTipoMovimiento() == TipoMovimiento.ENTRADA) {
                producto.setStock(producto.getStock() + detalleReq.getCantidad());
            } else if (request.getTipoMovimiento() == TipoMovimiento.SALIDA) {
                if (producto.getStock() < detalleReq.getCantidad()) {
                    throw new StockInsuficienteException("Stock insuficiente para el producto: " + producto.getNombre());
                }
                producto.setStock(producto.getStock() - detalleReq.getCantidad());
            } else if (request.getTipoMovimiento() == TipoMovimiento.TRANSFERENCIA) {
                if (producto.getStock() < detalleReq.getCantidad()) {
                    throw new StockInsuficienteException("Stock insuficiente para transferir: " + producto.getNombre());
                }
                // En transferencia el stock total no cambia, solo se mueve entre bodegas
            }

            productoRepository.save(producto);

            MovimientoDetalle detalle = MovimientoDetalle.builder()
                    .movimiento(movimiento)
                    .producto(producto)
                    .cantidad(detalleReq.getCantidad())
                    .build();

            movimiento.getDetalles().add(detalle);
        }

        movimiento = movimientoRepository.save(movimiento);
        auditoriaService.registrar("INSERT", username, "MovimientoInventario", null, movimiento.getId().toString());

        return toResponse(movimiento);
    }

    private MovimientoResponse toResponse(MovimientoInventario mov) {
        List<MovimientoResponse.DetalleResponse> detalles = mov.getDetalles().stream()
                .map(d -> MovimientoResponse.DetalleResponse.builder()
                        .producto(d.getProducto().getNombre())
                        .cantidad(d.getCantidad())
                        .build())
                .toList();

        return MovimientoResponse.builder()
                .id(mov.getId())
                .fecha(mov.getFecha())
                .tipoMovimiento(mov.getTipoMovimiento().name())
                .usuario(mov.getUsuario().getNombreCompleto())
                .bodegaOrigen(mov.getBodegaOrigen() != null ? mov.getBodegaOrigen().getNombre() : null)
                .bodegaDestino(mov.getBodegaDestino() != null ? mov.getBodegaDestino().getNombre() : null)
                .detalles(detalles)
                .build();
    }
}
