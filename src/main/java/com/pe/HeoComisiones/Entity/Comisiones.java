package com.pe.HeoComisiones.Entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Comisiones {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private double porcentaje;
    private double ganancia;
    @ManyToOne
    @JoinColumn(name="id_perfil")
    private Perfiles perfiles;
    @ManyToOne
    @JoinColumn(name="id_sucursal")
    private Sucursales sucursales;
    @ManyToOne
    @JoinColumn(name="id_usuario")
    private Usuarios usuarios;
    private boolean status;
}
