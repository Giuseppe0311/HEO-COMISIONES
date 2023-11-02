package com.pe.HeoComisiones.Repository;

import com.pe.HeoComisiones.Entity.Clientes;
import com.pe.HeoComisiones.Request.ClienteRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.query.Procedure;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository

public interface ClienteRepository extends JpaRepository<Clientes, Integer> {
List<Clientes> findByStatusTrue(Sort sort);
@Query(value = "SELECT * FROM getClientesByIdUsuario(:idusuario) order by id DESC", nativeQuery = true)

List<Clientes> getClientesbyUsuario(@Param("idusuario") Integer id);
}
