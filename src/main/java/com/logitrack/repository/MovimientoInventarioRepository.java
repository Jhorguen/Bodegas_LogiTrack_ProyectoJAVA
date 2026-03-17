package com.logitrack.repository;

import com.logitrack.model.MovimientoInventario;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDateTime;
import java.util.List;

public interface MovimientoInventarioRepository extends JpaRepository<MovimientoInventario, Long> {

    List<MovimientoInventario> findByFechaBetween(LocalDateTime inicio, LocalDateTime fin);
}
