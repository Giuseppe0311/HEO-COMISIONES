package com.pe.HeoComisiones.Entity;

import com.pe.HeoComisiones.Auditable.Auditable;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ResultTrabajadores extends Auditable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private double comisionTotal;
    private double montototal;
    private  double ganancia;
    @ManyToOne
    Usuarios usuarios;
}
