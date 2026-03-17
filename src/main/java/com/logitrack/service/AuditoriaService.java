package com.logitrack.service;

import com.logitrack.dto.AuditoriaResponse;
import com.logitrack.model.Auditoria;
import com.logitrack.model.enums.TipoOperacion;
import com.logitrack.repository.AuditoriaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AuditoriaService {

    private final AuditoriaRepository auditoriaRepository;

    public void registrar(String tipoOperacion, String usuario, String entidad,
                          String valoresAnteriores, String valoresNuevos) {
        Auditoria auditoria = Auditoria.builder()
                .tipoOperacion(TipoOperacion.valueOf(tipoOperacion))
                .usuario(usuario)
                .entidadAfectada(entidad)
                .valoresAnteriores(valoresAnteriores)
                .valoresNuevos(valoresNuevos)
                .build();

        auditoriaRepository.save(auditoria);
    }

    public List<AuditoriaResponse> listarTodas() {
        return auditoriaRepository.findAll().stream()
                .map(this::toResponse)
                .toList();
    }

    public List<AuditoriaResponse> listarPorUsuario(String usuario) {
        return auditoriaRepository.findByUsuario(usuario).stream()
                .map(this::toResponse)
                .toList();
    }

    public List<AuditoriaResponse> listarPorTipoOperacion(String tipo) {
        return auditoriaRepository.findByTipoOperacion(TipoOperacion.valueOf(tipo)).stream()
                .map(this::toResponse)
                .toList();
    }

    private AuditoriaResponse toResponse(Auditoria auditoria) {
        return AuditoriaResponse.builder()
                .id(auditoria.getId())
                .tipoOperacion(auditoria.getTipoOperacion().name())
                .fechaHora(auditoria.getFechaHora())
                .usuario(auditoria.getUsuario())
                .entidadAfectada(auditoria.getEntidadAfectada())
                .valoresAnteriores(auditoria.getValoresAnteriores())
                .valoresNuevos(auditoria.getValoresNuevos())
                .build();
    }
}
