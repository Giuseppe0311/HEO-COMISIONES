package com.pe.HeoComisiones.Repository;

import com.pe.HeoComisiones.Entity.Usuarios;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UsuarioRepository extends JpaRepository<Usuarios, Integer> {
}
