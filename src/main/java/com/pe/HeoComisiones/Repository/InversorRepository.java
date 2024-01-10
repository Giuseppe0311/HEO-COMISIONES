package com.pe.HeoComisiones.Repository;

import com.pe.HeoComisiones.Entity.Comisiones;
import com.pe.HeoComisiones.Entity.DetalleComisiones;
import com.pe.HeoComisiones.Entity.Inversor;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface InversorRepository extends JpaRepository<Inversor, Integer> {
    List<Inversor> findByStatusTrue(Sort sort);
    @Query(value = "SELECT * FROM getinversorbyidusuario(:idusuario)  where status=true order by id DESC", nativeQuery = true)
    List<Inversor> getInversionesByusuario(@Param("idusuario") Integer id);
    //TODO: Cambiar la sentencia para que solo actualice los inversores que el estado ya sea true
    @Modifying
    @Query("UPDATE Inversor i SET i.detalleComisiones = :detalleComisiones, i.status = false WHERE i.id IN :ids AND i.status = true ")
    void updateInversoresforDetalleComisiones(@Param("detalleComisiones") DetalleComisiones detalleComisiones, @Param("ids") List<Integer> ids);
    @Query(value = "SELECT id FROM getinversorbyidusuario(:idusuario) WHERE status = true ORDER BY id ASC", nativeQuery = true)
    List<Integer> getInversorIdsByUsuario(@Param("idusuario") Integer id);
}
