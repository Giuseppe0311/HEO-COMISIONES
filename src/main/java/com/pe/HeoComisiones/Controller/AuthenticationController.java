package com.pe.HeoComisiones.Controller;

import com.pe.HeoComisiones.DTOs.JwtResponse;
import com.pe.HeoComisiones.Request.UsuarioRequest;
import com.pe.HeoComisiones.Entity.RefreshToken;
import com.pe.HeoComisiones.Entity.Usuarios;
import com.pe.HeoComisiones.Request.RefreshTokenRequest;
import com.pe.HeoComisiones.Services.AuthenticationService;
import com.pe.HeoComisiones.Tokens.RefreshTokenService;
import com.pe.HeoComisiones.Tokens.JwtService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
public class AuthenticationController {
    @Autowired
    private RefreshTokenService refreshTokenService;
    @Autowired
    private JwtService jwtService;
    @Autowired
    private AuthenticationService authenticationService;
    @PostMapping("/login")
    public ResponseEntity<?> loginUser(@RequestBody UsuarioRequest usuarioRequest){
        try {
            return ResponseEntity.ok(authenticationService.loginUser(usuarioRequest.getUsername(), usuarioRequest.getPassword()));
        }catch (Exception e){
            //status 401
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Acceso denegado");

        }
    }
    @PostMapping("/register")
    public ResponseEntity<?> registerUser(@RequestBody UsuarioRequest usuarioRequest){
        try {
            authenticationService.registerUser(usuarioRequest.getUsername(), usuarioRequest.getPassword(),usuarioRequest.getDni(), usuarioRequest.getEmail(), usuarioRequest.getName(), usuarioRequest.getIdsucursal(), usuarioRequest.getPerfiles());
             return ResponseEntity.ok().build();
        }catch (Exception e){
            //status 401
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.toString());

        }
    }
    @PostMapping("/refreshtoken")
    public ResponseEntity<?> refrestoken(@RequestBody RefreshTokenRequest refreshTokenRequest) {
        try {
            RefreshToken refreshToken = refreshTokenService.findByToken(refreshTokenRequest.getRefreshtoken())
                    .orElseThrow(() -> new RuntimeException("Refresh Token is not in database!"));

            refreshTokenService.verifyExpiration(refreshToken);

            Usuarios user = refreshToken.getUsuario();
            String token = jwtService.generateToken(user.getUsername(),user.getAuthorities(),user.getId(),user.getSucursales().getId());

            JwtResponse jwtResponse = JwtResponse.builder()
                    .accessToken(token)
                    .refreshToken(refreshToken.getToken())
                    .build();

            return ResponseEntity.ok(jwtResponse);
        } catch (RuntimeException expiredException) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.toString());
        }
    }
}
