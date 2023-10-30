package com.pe.HeoComisiones.Repository;

import com.pe.HeoComisiones.Entity.Inversor;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface InversorRepository extends JpaRepository<Inversor, Integer> {
    List<Inversor> findByStatusTrue();
}
