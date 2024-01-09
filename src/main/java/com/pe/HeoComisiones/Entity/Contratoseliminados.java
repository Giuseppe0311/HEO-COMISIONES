package com.pe.HeoComisiones.Entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
public class Contratoseliminados {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idcontratoeliminado;
    @ManyToOne
    private Contratos contratos;
    @ManyToOne
    private Usuarios usuarios;
    private Date fechaeliminacion;
    private String motivodeeliminacion;

}
