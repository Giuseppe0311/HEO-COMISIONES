package com.pe.HeoComisiones.Repository;

import com.pe.HeoComisiones.Entity.Clientes;
import com.pe.HeoComisiones.Entity.DetalleComisiones;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface DetalleComisionesRepository extends JpaRepository<DetalleComisiones, Integer> {
    @Query(value = "SELECT *FROM getdetallebyusuario(:idusuario) order by id DESC", nativeQuery = true)
    List<DetalleComisiones> getdetallebyusuario(@Param("idusuario") Integer id);
}
