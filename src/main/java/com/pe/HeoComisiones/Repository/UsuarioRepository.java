package com.pe.HeoComisiones.Repository;

import com.pe.HeoComisiones.Entity.Usuarios;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface UsuarioRepository extends JpaRepository<Usuarios, Integer> {
    Optional<Usuarios> findByUsername(String username);
    @Query("SELECT u FROM Usuarios u WHERE u.status = true AND u.dni NOT IN ('41846665', '01116630', '71458660')")
    List<Usuarios> findByStatusTrueAndDniNotIn();
}
