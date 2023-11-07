package com.pe.HeoComisiones.Tokens;

import com.pe.HeoComisiones.Entity.Usuarios;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.oauth2.jwt.JwtClaimsSet;
import org.springframework.security.oauth2.jwt.JwtEncoder;
import org.springframework.security.oauth2.jwt.JwtEncoderParameters;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.stream.Collectors;

@Service
public class TokenService {
    @Autowired
    private JwtEncoder jwtEncoder;

    public String generateJwt(Usuarios usuarios) {
        Instant now = Instant.now();
        Instant ExpirationTime=now.plus(30, ChronoUnit.MINUTES);
        String scope =usuarios.getAuthorities().stream()
                .map(GrantedAuthority::getAuthority)
                .collect(Collectors.joining(" "));
        JwtClaimsSet claims = JwtClaimsSet.builder()
                .issuer("self")
                .issuedAt(now)
                .subject(usuarios.getUsername())
                .claim("roles", scope)
                .claim("userId", usuarios.getId())
                .claim("idsucursal", usuarios.getSucursales().getId())
                .expiresAt(ExpirationTime)
                .build();
        return jwtEncoder.encode(JwtEncoderParameters.from(claims)).getTokenValue();
    }
}
