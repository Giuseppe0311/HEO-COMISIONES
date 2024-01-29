package com.pe.HeoComisiones.DTOs.admin;

import java.util.Date;

public record AdminContratosDTO(
        Integer idcontrato,
        String codigoContrato,
        String urlContrato,
        Date fechaCreacion,
        String tipoContrato,
        String nombre_cliente,
        String dni_cliente,
        String tipo_documento_cliente,
        String tipo_moneda

) {
}
