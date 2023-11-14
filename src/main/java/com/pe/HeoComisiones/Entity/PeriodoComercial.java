package com.pe.HeoComisiones.Entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.List;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class PeriodoComercial {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private LocalDate mescomercial;

    @OneToMany(mappedBy = "periodoComercial")
    private List<DetalleComisiones> detalleComisiones;
}
