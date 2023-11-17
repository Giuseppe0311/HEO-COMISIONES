package com.pe.HeoComisiones.Services;

import com.pe.HeoComisiones.DTOs.ComisionesDTO;
import com.pe.HeoComisiones.DTOs.UsuarioDTO;
import com.pe.HeoComisiones.DTOs.admin.ComisionConUsuarioDTO;
import com.pe.HeoComisiones.Entity.Comisiones;
import com.pe.HeoComisiones.Entity.Usuarios;
import com.pe.HeoComisiones.Mappers.ComisionesDTOMapper;
import com.pe.HeoComisiones.Repository.ComisionRepository;
import com.pe.HeoComisiones.Repository.PerfilesRepository;
import com.pe.HeoComisiones.Repository.SucursalRepository;
import com.pe.HeoComisiones.Repository.UsuarioRepository;
import com.pe.HeoComisiones.Request.ComisionRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class ComisionService {
    private final ComisionesDTOMapper comisionesDTOMapper;
    @Autowired
    private ComisionRepository comisionRepository;
    @Autowired
    private UsuarioRepository usuariosRepository;
    @Autowired
    private PerfilesRepository perfilesRepository;
    @Autowired
    private SucursalRepository sucursalRepository;
    @Autowired
    private UsuarioRepository usuarioRepository;

    public ComisionService(ComisionesDTOMapper comisionesDTOMapper) {
        this.comisionesDTOMapper = comisionesDTOMapper;
    }

    public List<ComisionConUsuarioDTO> obtenerComisionesConUsuarios() {
        List<Comisiones> comisiones = comisionRepository.findAll();

        Map<Integer, UsuarioDTO> usuarioDTOMap = new HashMap<>();
        Map<Integer, List<ComisionesDTO>> comisionesPorUsuario = new HashMap<>();

        for (Comisiones comision : comisiones) {
            Usuarios usuario = comision.getUsuarios();

            usuarioDTOMap.computeIfAbsent(usuario.getId(), k -> new UsuarioDTO(
                    usuario.getId(),
                    usuario.getName(),
                    usuario.getEmail(),
                    usuario.getDni())
            );

            ComisionesDTO comisionesDTO = new ComisionesDTO(
                    comision.getId(),
                    comision.getPorcentaje(),
                    comision.getMontomax(),
                    comision.isStatus()
            );

            comisionesPorUsuario.computeIfAbsent(usuario.getId(), k -> new ArrayList<>())
                    .add(comisionesDTO);
        }

        List<ComisionConUsuarioDTO> resultado = new ArrayList<>();
        comisionesPorUsuario.forEach((userId, comisionesList) -> {
            UsuarioDTO usuarioDTO = usuarioDTOMap.get(userId);
            resultado.add(new ComisionConUsuarioDTO(
                    userId,
                    usuarioDTO,
                    comisionesList
            ));
        });

        return resultado;
    }

    public List<ComisionConUsuarioDTO> getComisionesByid(Integer id) {
        List<ComisionConUsuarioDTO> resultado = new ArrayList<>();
        Optional<Usuarios> usuarioOptional = usuariosRepository.findById(id);

        if (usuarioOptional.isPresent()) {
            Usuarios usuario = usuarioOptional.get();

            List<ComisionesDTO> comisionesDTOs = comisionRepository.getComisionbyusuario(id)
                    .stream()
                    .map(comision -> new ComisionesDTO(
                            comision.getId(),
                            comision.getPorcentaje(),
                            comision.getMontomax(),
                            comision.isStatus()
                    ))
                    .collect(Collectors.toList());

            UsuarioDTO usuarioDTO = new UsuarioDTO(
                    usuario.getId(),
                    usuario.getName(),
                    usuario.getEmail(),
                    usuario.getDni()
            );

            resultado.add(new ComisionConUsuarioDTO(
                    usuario.getId(),
                    usuarioDTO,
                    comisionesDTOs
            ));
        }

        return resultado;
    }

    //LADO DEL USUARIO/////////
    public List<ComisionesDTO> getComisionesByUsuario(Integer id) {
        return comisionRepository.getComisionbyusuario(id)
                .stream()
                .map(comisionesDTOMapper)
                .collect(Collectors.toList());
    }

    public void SaveComisiones(ComisionRequest comisionRequest) throws Exception {
        Comisiones comisiones = new Comisiones();
        comisiones.setPorcentaje(comisionRequest.getPorcentaje());
        comisiones.setMontomax(comisionRequest.getMontomax());
        comisiones.setUsuarios(usuarioRepository.findById(comisionRequest.getUsuarios()).orElse(null));
        comisiones.setStatus(true);
        comisionRepository.save(comisiones);
    }

    public void UpdateComisiones(Integer id, ComisionRequest comisionRequest) throws Exception {
        Comisiones comisiones1 = comisionRepository.findById(id).orElse(null);
        if (comisiones1 != null) {
            comisiones1.setPorcentaje(comisionRequest.getPorcentaje());
            comisiones1.setMontomax(comisionRequest.getMontomax());
            comisiones1.setUsuarios(usuarioRepository.findById(comisionRequest.getUsuarios()).orElse(null));
            comisionRepository.save(comisiones1);
        }
        throw new Exception();
    }

    public void DeleteComisiones(Integer id) throws Exception {
        Comisiones comisiones = comisionRepository.findById(id).orElse(null);
        if (comisiones != null) {
            comisiones.setStatus(false);
            comisionRepository.save(comisiones);
        }
        throw new Exception();
    }


}
