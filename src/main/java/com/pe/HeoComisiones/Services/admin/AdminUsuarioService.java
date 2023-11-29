package com.pe.HeoComisiones.Services.admin;

import com.pe.HeoComisiones.DTOs.admin.Admin_UserDTO;
import com.pe.HeoComisiones.Entity.Usuarios;
import com.pe.HeoComisiones.Request.UsuarioRequest;

import java.util.List;

public interface AdminUsuarioService {
    List<Admin_UserDTO> getUsuariosforAdmin();
    List<Admin_UserDTO> getUsuariosforAdminwithoutcomisiones();
    Admin_UserDTO getUsuariosbyIdforAdmin(Integer id);

    void updateUsuario(UsuarioRequest usuarioRequest, Integer id);
    void deleteUser(Integer id);
}
