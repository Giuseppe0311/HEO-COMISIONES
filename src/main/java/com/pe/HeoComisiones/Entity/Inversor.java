package com.pe.HeoComisiones.Entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Entity
@NoArgsConstructor
@Data
@AllArgsConstructor
public class Inversor {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @ManyToOne
    @JoinColumn(name="id_cliente")
    private Clientes clientes;
    private double montoinvertido;
    private String contrato;
    private boolean status;
    @ManyToOne
    @JsonIgnore
    @ToString.Exclude// Asegúrate de que este nombre de columna sea correcto
    private DetalleComisiones detalleComisiones;
    @ManyToOne
    @JoinColumn(name="id_usuario")
    private Usuarios usuarios;
}
