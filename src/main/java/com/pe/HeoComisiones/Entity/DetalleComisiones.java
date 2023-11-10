package com.pe.HeoComisiones.Entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

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
    @ManyToOne
    private Usuarios usuarios;
    private Date mescomercial;
    @PrePersist
    public void prePersist(){
        mescomercial = new Date();
    }
}
