package com.logitrack.repository;

import com.logitrack.model.Auditoria;
import com.logitrack.model.enums.TipoOperacion;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AuditoriaRepository extends JpaRepository<Auditoria, Long> {

    List<Auditoria> findByUsuario(String usuario);

    List<Auditoria> findByTipoOperacion(TipoOperacion tipoOperacion);
}
