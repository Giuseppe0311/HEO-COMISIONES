package com.pe.HeoComisiones.Mappers.admin;


import com.pe.HeoComisiones.DTOs.UsuarioDTO;
import com.pe.HeoComisiones.DTOs.admin.Admin_UserDTO;
import com.pe.HeoComisiones.Entity.Usuarios;
import org.springframework.stereotype.Service;

import java.util.Set;
import java.util.function.Function;

@Service
public class Admin_UserDTOMapper implements Function<Usuarios, Admin_UserDTO> {
    @Override
    public Admin_UserDTO apply(Usuarios usuarios) {
        return  new Admin_UserDTO(
                usuarios.getId(),
                usuarios.getName(),
                usuarios.getUsername(),
                usuarios.getDni(),
                usuarios.getEmail(),
                usuarios.getProfiles(),
                usuarios.getSucursales(),
                usuarios.getCreadopor(),
                usuarios.getFechacreacion(),
                usuarios.getUltimaactualizacionpor(),
                usuarios.getFechaultimaactualizacion()
        );
    }
}
