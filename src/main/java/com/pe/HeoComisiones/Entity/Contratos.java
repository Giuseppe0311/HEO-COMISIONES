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
public class Contratos {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idcontrato;
    @ManyToOne
    private Usuarios usuarios;
    private Integer numeroSecueciaContrato;
    private String codigoContrato;
    private String urlContrato;
    private Date fechaCreacion;
    private String tipoContrato;
    private  String nombre_cliente;
    private String  dni_cliente;
    private String tipo_documento_cliente;
    private String tipo_moneda;
    private boolean estado;
}
