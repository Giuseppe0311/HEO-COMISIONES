package com.pe.HeoComisiones.DTOs;

public record InversorDTO(
         Integer id,
        ClienteDTO clientes,
         double montoinvertido,
         String contrato,
         boolean status
) {

}
