package com.pe.HeoComisiones.Repository;

import com.pe.HeoComisiones.Entity.RefreshToken;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RefreshTokenRepository extends JpaRepository<RefreshToken,Integer>{
    Optional<RefreshToken> findByToken(String token);

}
