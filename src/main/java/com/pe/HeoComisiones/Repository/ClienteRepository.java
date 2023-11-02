package com.pe.HeoComisiones.Repository;

import com.pe.HeoComisiones.Entity.Clientes;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository

public interface ClienteRepository extends JpaRepository<Clientes, Integer> {
List<Clientes> findByStatusTrue(Sort sort);
List<Clientes> findByStatusTrueAndIdUsuario(Integer idUsuario, Sort sort);

}
