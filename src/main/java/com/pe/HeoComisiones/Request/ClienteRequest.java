package com.pe.HeoComisiones.Request;

import com.pe.HeoComisiones.Entity.Usuarios;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ClienteRequest {
    private String nombre;
    private String apellido;
    private String dni;
    private String telefono;
    private String distrito;
    private String provincia;
    private String departamento;
    private String correoelectronico;
    private Integer usuarios;
}
