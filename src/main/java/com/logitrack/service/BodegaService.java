package com.logitrack.service;

import com.logitrack.dto.BodegaRequest;
import com.logitrack.dto.BodegaResponse;
import com.logitrack.model.Bodega;
import com.logitrack.repository.BodegaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class BodegaService {

    private final BodegaRepository bodegaRepository;
    private final AuditoriaService auditoriaService;

    public List<BodegaResponse> listarTodas() {
        return bodegaRepository.findAll().stream()
                .map(this::toResponse)
                .toList();
    }

    public BodegaResponse obtenerPorId(Long id) {
        Bodega bodega = bodegaRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Bodega no encontrada con id: " + id));
        return toResponse(bodega);
    }

    public BodegaResponse crear(BodegaRequest request, String usuario) {
        Bodega bodega = Bodega.builder()
                .nombre(request.getNombre())
                .ubicacion(request.getUbicacion())
                .capacidad(request.getCapacidad())
                .encargado(request.getEncargado())
                .build();

        bodega = bodegaRepository.save(bodega);
        auditoriaService.registrar("INSERT", usuario, "Bodega", null, bodega.toString());
        return toResponse(bodega);
    }

    public BodegaResponse actualizar(Long id, BodegaRequest request, String usuario) {
        Bodega bodega = bodegaRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Bodega no encontrada con id: " + id));

        String valoresAnteriores = bodega.toString();

        bodega.setNombre(request.getNombre());
        bodega.setUbicacion(request.getUbicacion());
        bodega.setCapacidad(request.getCapacidad());
        bodega.setEncargado(request.getEncargado());

        bodega = bodegaRepository.save(bodega);
        auditoriaService.registrar("UPDATE", usuario, "Bodega", valoresAnteriores, bodega.toString());
        return toResponse(bodega);
    }

    public void eliminar(Long id, String usuario) {
        Bodega bodega = bodegaRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Bodega no encontrada con id: " + id));

        auditoriaService.registrar("DELETE", usuario, "Bodega", bodega.toString(), null);
        bodegaRepository.delete(bodega);
    }

    private BodegaResponse toResponse(Bodega bodega) {
        return BodegaResponse.builder()
                .id(bodega.getId())
                .nombre(bodega.getNombre())
                .ubicacion(bodega.getUbicacion())
                .capacidad(bodega.getCapacidad())
                .encargado(bodega.getEncargado())
                .build();
    }
}
