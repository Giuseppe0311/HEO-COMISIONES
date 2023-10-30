package com.pe.HeoComisiones.Entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class DetalleComisiones {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @ManyToOne
    @JoinColumn(name="id_inversor")
    private Inversor inversor;
    @ManyToOne
    private ResultTrabajadores resultTrabajadores;
    private String mescomercial;
    @ManyToOne
    private Usuarios usuarios;
}
