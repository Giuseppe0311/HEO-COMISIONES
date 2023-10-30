package com.pe.HeoComisiones.Repository;

import com.pe.HeoComisiones.Entity.Cliente;
import com.pe.HeoComisiones.Entity.Comision;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ComisionRepository extends JpaRepository<Comision, Integer>{
    List<Comision> findByStatusTrue();
}
