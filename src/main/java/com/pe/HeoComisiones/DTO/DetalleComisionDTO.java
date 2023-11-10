package com.pe.HeoComisiones.DTO;

import java.util.Date;

public record DetalleComisionDTO(
       Integer id,
       InversorDTO inversor,
       ResulTrabajadoresDTO resulttrabajadores,
       Date mescomercial
) {
}
