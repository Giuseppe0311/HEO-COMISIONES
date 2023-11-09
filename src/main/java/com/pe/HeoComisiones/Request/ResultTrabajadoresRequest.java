package com.pe.HeoComisiones.Request;


import com.pe.HeoComisiones.Entity.Usuarios;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ResultTrabajadoresRequest {
    private double comisionTotal;
    private double montototal;
    private  double ganancia;
    private Integer idusuario;
}
