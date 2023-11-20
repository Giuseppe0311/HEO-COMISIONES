package com.pe.HeoComisiones.Request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ComisionRequest {
    Integer id;
    private double porcentaje;
    private double montomax;
    private Integer usuarios;

}
