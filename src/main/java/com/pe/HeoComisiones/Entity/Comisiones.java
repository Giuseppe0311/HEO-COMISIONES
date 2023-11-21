package com.pe.HeoComisiones.Entity;

import com.pe.HeoComisiones.Auditable.Auditable;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Comisiones extends Auditable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private double porcentaje;
    private double montomax;
    @ManyToOne
    @JoinColumn(name="id_usuario")
    private Usuarios usuarios;
    private boolean status;
}
