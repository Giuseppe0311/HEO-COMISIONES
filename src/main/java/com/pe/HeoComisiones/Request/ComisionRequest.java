package com.pe.HeoComisiones.Request;

import com.pe.HeoComisiones.Entity.Perfiles;
import com.pe.HeoComisiones.Entity.Sucursal;
import com.pe.HeoComisiones.Entity.Usuarios;
import jakarta.persistence.*;
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
