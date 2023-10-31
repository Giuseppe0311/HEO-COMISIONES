package com.pe.HeoComisiones.Request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ComisionRequest {

    private double porcentaje;
    private double ganancia;

    private Integer perfiles;

    private Integer sucursal;

    private Integer usuarios;

}
