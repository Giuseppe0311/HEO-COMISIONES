package com.pe.HeoComisiones.Repository;

import com.pe.HeoComisiones.Entity.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UsuarioRepository extends JpaRepository<Usuario, Integer> {
}
