package com.pe.HeoComisiones.Repository;

import com.pe.HeoComisiones.Entity.Comisiones;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ComisionRepository extends JpaRepository<Comisiones, Integer>{
    List<Comisiones> findByStatusTrue();
}
