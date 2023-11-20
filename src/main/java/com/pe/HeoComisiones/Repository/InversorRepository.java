package com.pe.HeoComisiones.Repository;

import com.pe.HeoComisiones.Entity.Comisiones;
import com.pe.HeoComisiones.Entity.Inversor;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface InversorRepository extends JpaRepository<Inversor, Integer> {
    List<Inversor> findByStatusTrue(Sort sort);
    @Query(value = "SELECT * FROM getinversorbyidusuario(:idusuario)  where status=true order by id DESC", nativeQuery = true)
    List<Inversor> getInversionesByusuario(@Param("idusuario") Integer id);
    @Query(value="select delteinversorbyusuario(:idusuario)",nativeQuery = true)
    void deleteInversorbyUsuario(@Param("idusuario") Integer id);

}
