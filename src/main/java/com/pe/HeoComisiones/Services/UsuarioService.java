package com.pe.HeoComisiones.Services;


import com.pe.HeoComisiones.DTOs.admin.Admin_UserDTO;
import com.pe.HeoComisiones.Entity.Perfiles;
import com.pe.HeoComisiones.Entity.Usuarios;
import com.pe.HeoComisiones.Mappers.admin.Admin_UserDTOMapper;
import com.pe.HeoComisiones.Repository.PerfilesRepository;
import com.pe.HeoComisiones.Repository.SucursalRepository;
import com.pe.HeoComisiones.Repository.UsuarioRepository;
import com.pe.HeoComisiones.Request.UsuarioRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class UsuarioService {
    private final Admin_UserDTOMapper admin_userDTOMapper;
    @Autowired
    private UsuarioRepository usuarioRepository;
    @Autowired
    PasswordEncoder passwordEncoder;
    @Autowired
    private PerfilesRepository perfilesRepository;
    @Autowired
    private SucursalRepository sucursalRepository;

    public UsuarioService(Admin_UserDTOMapper adminUserDTOMapper) {
        admin_userDTOMapper = adminUserDTOMapper;
    }

    public List<Admin_UserDTO> getUsuariosforAdmin() {
        return usuarioRepository.findByStatusTrue()
                .stream()
                .map(admin_userDTOMapper)
                .collect(Collectors.toList());
    }

    public List<Admin_UserDTO> getUsuariosbyId(Integer id) throws Exception {
        Usuarios usuarios = usuarioRepository.findById(id).orElse(null);
        List<Admin_UserDTO> admin_userDTOS = new ArrayList<>();
        if (usuarios != null) {
            admin_userDTOS.add(admin_userDTOMapper.apply(usuarios));
            return admin_userDTOS;
        }
        return admin_userDTOS;
    }
    public  void updateUsuario(UsuarioRequest usuarioRequest, Integer id) throws Exception {
        Usuarios usuarios = usuarioRepository.findById(id).orElse(null);
        Set<Perfiles> perfiles = new HashSet<>();
        for (Integer perfilid : usuarioRequest.getPerfiles()) {
            Perfiles perfil = perfilesRepository.findById(perfilid).orElseThrow(() ->
                    new RuntimeException("Error: Perfil is not found."));
            perfiles.add(perfil);
        }
        if (usuarios != null) {
            usuarios.setUsername(usuarioRequest.getUsername());
            usuarios.setPassword(passwordEncoder.encode(usuarioRequest.getPassword()));
            usuarios.setDni(usuarioRequest.getDni());
            usuarios.setEmail(usuarioRequest.getEmail());
            usuarios.setName(usuarioRequest.getName());
            usuarios.setProfiles(perfiles);
            usuarios.setSucursales(sucursalRepository.findById(usuarioRequest.getIdsucursal()).orElse(null));
            usuarioRepository.save(usuarios);
        }
        throw new Exception("Usuario no encontrado");
    }
    public void deleteUser(Integer id) throws Exception {
        Usuarios usuarios = usuarioRepository.findById(id).orElse(null);
        if (usuarios != null) {
            usuarios.setStatus(false);
            usuarioRepository.save(usuarios);
        }
        throw new Exception("Usuario no encontrado");
    }

}
