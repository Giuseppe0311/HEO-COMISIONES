package com.pe.HeoComisiones.DTOs.admin;

import com.pe.HeoComisiones.Entity.Perfiles;
import com.pe.HeoComisiones.Entity.Sucursales;

import java.util.Date;
import java.util.Set;

public record Admin_UserDTO(
        Integer id,
        String name,
        String username,
        String dni,
        String email,
        Set<Perfiles> perfiles,
        Sucursales sucursales,
        String creadopor,
        Date fechacreacion,
        String ultimaactualizacionpor,
        Date fechaultimaactualizacion
) {
}
