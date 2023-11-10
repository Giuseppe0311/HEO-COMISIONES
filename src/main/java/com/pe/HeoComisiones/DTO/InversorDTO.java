package com.pe.HeoComisiones.DTO;

public record InversorDTO(
         Integer id,
        ClienteDTO clientes,
         double montoinvertido,
         String contrato,
         boolean status
) {

}
