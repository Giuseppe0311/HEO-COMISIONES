package com.pe.HeoComisiones.DTOs.admin;

import com.pe.HeoComisiones.DTOs.ClienteDTO;
import com.pe.HeoComisiones.DTOs.UsuarioDTO;

public record Admin_InversoresDTO(
        Integer id,
        ClienteDTO clientes,
        UsuarioDTO usuario,
        double montoinvertido,
        String contrato,
        boolean status,
        String creadopor,
        String fechacreacion,
        String ultimaactualizacionpor,
        String fechaultimaactualizacion
) {
}
