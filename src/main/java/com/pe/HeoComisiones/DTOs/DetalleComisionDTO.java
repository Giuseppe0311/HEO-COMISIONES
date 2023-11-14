package com.pe.HeoComisiones.DTOs;

import java.sql.Date;
import java.util.List;

public record DetalleComisionDTO(
        Integer id,
        List<InversorDTO> inversores,
        ResulTrabajadoresDTO resulttrabajadores,
        UsuarioDTO usuario,
         Date mescomercial
) {
}
