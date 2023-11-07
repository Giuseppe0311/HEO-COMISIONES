package com.pe.HeoComisiones.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UsuarioDTO {
    private Integer id;
    private String name;
    private String username;
    private String email;
    private String password;
    private Set<Integer> perfiles;
    private Integer idsucursal;

}
