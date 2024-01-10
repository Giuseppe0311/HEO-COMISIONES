package com.pe.HeoComisiones.Repository;

import com.pe.HeoComisiones.Entity.Comisiones;
import com.pe.HeoComisiones.Entity.DetalleComisiones;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ComisionRepository extends JpaRepository<Comisiones, Integer>{
    List<Comisiones> findByStatusTrue();
    @Query(value = "select *from getcomisionbyusuario(:idusuario) order by id ASC", nativeQuery = true)
    List<Comisiones> getComisionbyusuario(@Param("idusuario") Integer id);
}
