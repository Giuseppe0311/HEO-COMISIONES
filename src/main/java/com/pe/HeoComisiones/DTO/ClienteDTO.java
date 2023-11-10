package com.pe.HeoComisiones.DTO;

public record ClienteDTO(
         Integer id,
         String nombre,
         String apellido,
         String dni,
         String telefono,
         String distrito,
         String provincia,
         String departamento
) {
}
