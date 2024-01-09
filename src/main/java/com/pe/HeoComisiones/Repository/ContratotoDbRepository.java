package com.pe.HeoComisiones.Repository;

import com.pe.HeoComisiones.Entity.Contratos;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface ContratotoDbRepository extends JpaRepository<Contratos,Integer> {
    @Query(value = "SELECT * FROM contratos WHERE usuarios_id = ? ORDER BY numero_secuecia_contrato DESC LIMIT 1;",nativeQuery = true)
    Contratos findLastContrato(Integer idusuario);
}
