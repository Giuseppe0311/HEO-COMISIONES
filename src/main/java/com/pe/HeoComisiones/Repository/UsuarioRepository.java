package com.pe.HeoComisiones.Repository;

import com.pe.HeoComisiones.Entity.Usuarios;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UsuarioRepository extends JpaRepository<Usuarios, Integer> {
    Optional<Usuarios> findByUsername(String username);
    @Query("SELECT u FROM Usuarios u WHERE u.status = true AND u.dni NOT IN ('41846665', '01116630', '40070789')")
    List<Usuarios> findByStatusTrueAndDniNotIn();
    @Query(value = "SELECT u.* " +
            "FROM Usuarios u " +
            "LEFT JOIN Comisiones c ON u.id = c.id_usuario " +
            "WHERE u.status = true AND c.id_usuario IS NULL AND u.dni NOT IN ('01116630', '41846665', '40070789')",
            nativeQuery = true)
    List<Usuarios> findUsuariosSinComisiones();
}
