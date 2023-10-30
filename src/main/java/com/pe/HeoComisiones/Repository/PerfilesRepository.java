package com.pe.HeoComisiones.Repository;

import com.pe.HeoComisiones.Entity.Perfiles;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PerfilesRepository extends JpaRepository<Perfiles,Integer> {
    List<Perfiles> findByStatusTrue();
}
