package com.pe.HeoComisiones.Request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UsuarioRequest {
    private String name;
    private String dni;
    private String username;
    private String email;
    private String password;
    private Set<Integer> perfiles;
    private Integer idsucursal;

}
