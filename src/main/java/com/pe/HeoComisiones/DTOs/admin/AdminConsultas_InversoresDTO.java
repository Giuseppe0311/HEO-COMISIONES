package com.pe.HeoComisiones.DTOs.admin;

import com.pe.HeoComisiones.DTOs.InversorDTO;
import com.pe.HeoComisiones.DTOs.UsuarioDTO;
import com.pe.HeoComisiones.Entity.Inversor;

import java.util.List;

public record AdminConsultas_InversoresDTO(
        Integer idusuario,
        List<InversorDTO> inversores,
        UsuarioDTO usuario
) {
}
