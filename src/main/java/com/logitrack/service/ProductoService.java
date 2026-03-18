package com.logitrack.service;

import com.logitrack.dto.ProductoRequest;
import com.logitrack.dto.ProductoResponse;
import com.logitrack.exception.ResourceNotFoundException;
import com.logitrack.model.Producto;
import com.logitrack.repository.ProductoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ProductoService {

    private final ProductoRepository productoRepository;
    private final AuditoriaService auditoriaService;

    public List<ProductoResponse> listarTodos() {
        return productoRepository.findAll().stream()
                .map(this::toResponse)
                .toList();
    }

    public ProductoResponse obtenerPorId(Long id) {
        Producto producto = productoRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Producto no encontrado con id: " + id));
        return toResponse(producto);
    }

    public List<ProductoResponse> obtenerStockBajo() {
        return productoRepository.findByStockLessThan(10).stream()
                .map(this::toResponse)
                .toList();
    }

    public ProductoResponse crear(ProductoRequest request, String usuario) {
        Producto producto = Producto.builder()
                .nombre(request.getNombre())
                .categoria(request.getCategoria())
                .stock(request.getStock())
                .precio(request.getPrecio())
                .build();

        producto = productoRepository.save(producto);
        auditoriaService.registrar("INSERT", usuario, "Producto", null, producto.toString());
        return toResponse(producto);
    }

    public ProductoResponse actualizar(Long id, ProductoRequest request, String usuario) {
        Producto producto = productoRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Producto no encontrado con id: " + id));

        String valoresAnteriores = producto.toString();

        producto.setNombre(request.getNombre());
        producto.setCategoria(request.getCategoria());
        producto.setStock(request.getStock());
        producto.setPrecio(request.getPrecio());

        producto = productoRepository.save(producto);
        auditoriaService.registrar("UPDATE", usuario, "Producto", valoresAnteriores, producto.toString());
        return toResponse(producto);
    }

    public void eliminar(Long id, String usuario) {
        Producto producto = productoRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Producto no encontrado con id: " + id));

        auditoriaService.registrar("DELETE", usuario, "Producto", producto.toString(), null);
        productoRepository.delete(producto);
    }

    private ProductoResponse toResponse(Producto producto) {
        return ProductoResponse.builder()
                .id(producto.getId())
                .nombre(producto.getNombre())
                .categoria(producto.getCategoria())
                .stock(producto.getStock())
                .precio(producto.getPrecio())
                .build();
    }
}
