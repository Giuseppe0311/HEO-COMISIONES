package com.pe.HeoComisiones.Request;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class InversorRequest {
    private Integer idcliente;
    private double montoinvertido;
    private String contrato;
    Integer iddetallecomisiones;
    private Integer idusuario;
}
