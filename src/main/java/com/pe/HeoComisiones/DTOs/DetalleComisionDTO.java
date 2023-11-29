package com.pe.HeoComisiones.DTOs;

import java.util.Date;
import java.util.List;

public record DetalleComisionDTO(
        Integer id,
        List<InversorDTO> inversores,
        ResulTrabajadoresDTO resulttrabajadores,
        UsuarioDTO usuario,
        String creadopor,
        Date fechacreacion,
        String ultimaactualizacionpor,
        Date fechaultimaactualizacion
) {
}
