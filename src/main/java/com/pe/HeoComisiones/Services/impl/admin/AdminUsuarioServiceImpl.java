package com.pe.HeoComisiones.Services.impl.admin;

import com.pe.HeoComisiones.DTOs.admin.Admin_UserDTO;
import com.pe.HeoComisiones.Entity.Perfiles;
import com.pe.HeoComisiones.Entity.Usuarios;
import com.pe.HeoComisiones.Exception.PerfilNotFoundException;
import com.pe.HeoComisiones.Exception.UsuarioNotFoundException;
import com.pe.HeoComisiones.Mappers.admin.Admin_UserDTOMapper;
import com.pe.HeoComisiones.Repository.PerfilesRepository;
import com.pe.HeoComisiones.Repository.UsuarioRepository;
import com.pe.HeoComisiones.Request.UsuarioRequest;
import com.pe.HeoComisiones.Services.admin.AdminSucursalesService;
import com.pe.HeoComisiones.Services.admin.AdminUsuarioService;
import com.pe.HeoComisiones.Services.common.CommonUsuarioService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class AdminUsuarioServiceImpl implements AdminUsuarioService, CommonUsuarioService {
    private final Admin_UserDTOMapper admin_userDTOMapper;
    private final UsuarioRepository usuarioRepository;
    private final PasswordEncoder passwordEncoder;
    private final PerfilesRepository perfilesRepository;
    private  final AdminSucursalesService adminSucursalesService;
    //PARTE PARA EL ADMIN
    @Override
    public List<Admin_UserDTO> getUsuariosforAdmin() {
        return usuarioRepository.findByStatusTrueAndDniNotIn()
                .stream()
                .map(admin_userDTOMapper)
                .collect(Collectors.toList());
    }

    @Override
    public List<Admin_UserDTO> getUsuariosforAdminwithoutcomisiones() {
        return usuarioRepository.findUsuariosSinComisiones()
                .stream()
                .map(admin_userDTOMapper)
                .collect(Collectors.toList());
    }

    @Override
    public Admin_UserDTO getUsuariosbyIdforAdmin(Integer id) {
        Usuarios usuarios = verifyUsuarioExistsById(id);
        return admin_userDTOMapper.apply(usuarios);
    }



    @Override
    @Transactional
    public void updateUsuario(UsuarioRequest usuarioRequest, Integer id) {
        Usuarios usuarios = verifyUsuarioExistsById(id);
        Set<Perfiles> perfiles = usuarioRequest.getPerfiles().stream()
                .map(perfil -> perfilesRepository.findById(perfil).orElseThrow(() ->
                        new PerfilNotFoundException("No se encontró el Perfil")))
                .collect(Collectors.toSet());
        if (usuarioRequest.getPassword() != null && !usuarioRequest.getPassword().isEmpty()) {
            usuarios.setPassword(passwordEncoder.encode(usuarioRequest.getPassword()));
        }
        usuarios.setDni(usuarioRequest.getDni());
        usuarios.setEmail(usuarioRequest.getEmail());
        usuarios.setName(usuarioRequest.getName());
        usuarios.setProfiles(perfiles);
        usuarios.setSucursales(adminSucursalesService.verifySucursalExistsById(usuarioRequest.getIdsucursal()));
        usuarioRepository.save(usuarios);
    }

    @Override
    public void deleteUser(Integer id) {
        Usuarios usuarios = verifyUsuarioExistsById(id);
        usuarios.setStatus(false);
        usuarioRepository.save(usuarios);
    }

    @Override
    public Usuarios verifyUsuarioExistsById(Integer id) {
        return usuarioRepository.findById(id).orElseThrow(() ->
                new UsuarioNotFoundException("No se encontró el usuario"));
    }
}
