package com.pe.HeoComisiones.Entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.sql.Date;
import java.util.List;


@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class DetalleComisiones {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @OneToMany(fetch = FetchType.EAGER, mappedBy = "detalleComisiones")
    @ToString.Exclude
    private List<Inversor> inversores;

    @OneToOne
    private ResultTrabajadores resultTrabajadores;

    @OneToOne
    private Usuarios usuarios;

    private Date mescomercial;

    @PrePersist
    public void prePersist(){
        mescomercial = new Date(System.currentTimeMillis());
    }

}
