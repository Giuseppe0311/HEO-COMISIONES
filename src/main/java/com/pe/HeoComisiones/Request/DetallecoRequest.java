package com.pe.HeoComisiones.Request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class DetallecoRequest {
    private  Integer inversor;
    private  Integer resultTrabajadores;
    private  String mescomercial;
    private  Integer usuario;
}
