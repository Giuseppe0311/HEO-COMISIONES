package com.pe.HeoComisiones.DTOs.admin;

import com.pe.HeoComisiones.Entity.Perfiles;
import com.pe.HeoComisiones.Entity.Sucursales;

import java.util.Set;

public record Admin_UserDTO(
        String name,
        String username,
        String dni,
        String email,
        Set<Perfiles> perfiles,
        Sucursales sucursales
) {
}
