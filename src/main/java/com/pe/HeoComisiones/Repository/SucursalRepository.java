package com.pe.HeoComisiones.Repository;

import com.pe.HeoComisiones.Entity.Sucursales;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SucursalRepository extends JpaRepository<Sucursales, Integer> {
    List<Sucursales> findByStatusTrue();
}
