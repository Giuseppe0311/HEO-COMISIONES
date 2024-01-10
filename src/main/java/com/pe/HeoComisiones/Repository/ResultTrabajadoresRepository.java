package com.pe.HeoComisiones.Repository;

import com.pe.HeoComisiones.Entity.ResultTrabajadores;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ResultTrabajadoresRepository extends JpaRepository<ResultTrabajadores, Integer> {
}
