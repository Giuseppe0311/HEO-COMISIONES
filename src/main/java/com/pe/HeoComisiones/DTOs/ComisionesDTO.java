package com.pe.HeoComisiones.DTOs;

public record ComisionesDTO(
         Integer id,
         double porcentaje,
         double montomax,
         boolean status
){
}
