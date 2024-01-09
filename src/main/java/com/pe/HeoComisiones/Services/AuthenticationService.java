package com.pe.HeoComisiones.Services;

import com.pe.HeoComisiones.DTOs.JwtResponse;
import com.pe.HeoComisiones.Entity.Perfiles;
import com.pe.HeoComisiones.Entity.RefreshToken;
import com.pe.HeoComisiones.Entity.Sucursales;
import com.pe.HeoComisiones.Entity.Usuarios;
import com.pe.HeoComisiones.Repository.PerfilesRepository;
import com.pe.HeoComisiones.Repository.SucursalRepository;
import com.pe.HeoComisiones.Repository.UsuarioRepository;
import com.pe.HeoComisiones.Tokens.RefreshTokenService;
import com.pe.HeoComisiones.Tokens.JwtService;
import com.pe.HeoComisiones.Validations.ValidationUtils;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

@Service
@RequiredArgsConstructor
@Transactional
public class AuthenticationService {
    private final RefreshTokenService refreshTokenService;
    //EL CODIGO EN REALIAD DEBERIA ESTAR ASI :

    private final UsuarioRepository userRepository;
    private final PerfilesRepository perfilesRepository;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;
    private final  SucursalRepository sucursalRepository;
    private final JwtService jwtService;

    public void registerUser(String username, String password, String dni, String email, String name, Integer idSucursal, Set<Integer> perfiles) {
        if (ValidationUtils.DniAlreadyExistforUser(userRepository.findAll(), dni)) {
            throw new RuntimeException("Error: DNI is already in use!");
        }
        String encodedPassword = passwordEncoder.encode(password);
        Set<Perfiles> authorities = new HashSet<>();

        for (Integer perfilid : perfiles) {
            Perfiles perfil = perfilesRepository.findById(perfilid).orElseThrow(() ->
                    new RuntimeException("Error: Perfil is not found."));
            authorities.add(perfil);
        }
        Sucursales sucursales = sucursalRepository.findById(idSucursal).orElse(null);
        if (sucursales != null) {
            Usuarios usuario = new Usuarios();
            usuario.setUsername(username);
            usuario.setPassword(encodedPassword);
            usuario.setDni(dni);
            usuario.setEmail(email);
            usuario.setName(name);
            usuario.setSucursales(sucursales);
            usuario.setStatus(true);
            usuario.setProfiles(authorities);
            userRepository.save(usuario);
        } else {
            throw new RuntimeException("Error: Sucursal is not found.");
        }
    }

    public JwtResponse loginUser(String username, String password) {
        try {
            Authentication authentication = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(username, password));
            UserDetails userDetails = (UserDetails) authentication.getPrincipal();
            Usuarios user = userRepository.findByUsername(userDetails.getUsername()).orElse(null);
            RefreshToken createRefreshToken = refreshTokenService.createRefreshToken(username);
            if (user != null) {
                String jwt = jwtService.generateToken(userDetails.getUsername(), userDetails.getAuthorities(), user.getId(), user.getSucursales().getId());
                return new JwtResponse(jwt, createRefreshToken.getToken());
            } else {
                throw new Exception("Error: User not found.");
            }
        } catch (Exception e) {
            throw new RuntimeException("Error: " + e.getMessage());
        }
    }

}
