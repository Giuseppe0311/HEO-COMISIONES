package com.pe.HeoComisiones.Services.impl.admin;

import com.pe.HeoComisiones.DTOs.ComisionesDTO;
import com.pe.HeoComisiones.DTOs.UsuarioDTO;
import com.pe.HeoComisiones.DTOs.admin.ComisionConUsuarioDTO;
import com.pe.HeoComisiones.Entity.Comisiones;
import com.pe.HeoComisiones.Entity.Perfiles;
import com.pe.HeoComisiones.Entity.Usuarios;
import com.pe.HeoComisiones.Exception.ComisionNotFoundException;
import com.pe.HeoComisiones.Repository.*;
import com.pe.HeoComisiones.Request.ComisionRequest;
import com.pe.HeoComisiones.Services.admin.AdminComisionesService;
import com.pe.HeoComisiones.Services.admin.AdminUsuarioService;
import com.pe.HeoComisiones.Services.common.CommonUsuarioService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class AdminComisionesServiceImpl implements AdminComisionesService {
    private final ComisionRepository comisionRepository;
    private final CommonUsuarioService commonUsuarioService;

    @Override
    public List<ComisionConUsuarioDTO> obtenerComisionesConUsuarios() {
        Sort sort = Sort.by(Sort.Direction.ASC, "id");
        List<Comisiones> comisiones = comisionRepository.findAll(sort);

        Map<Integer, UsuarioDTO> usuarioDTOMap = new HashMap<>();
        Map<Integer, List<ComisionesDTO>> comisionesPorUsuario = new HashMap<>();

        for (Comisiones comision : comisiones) {
            Usuarios usuario = comision.getUsuarios();

            usuarioDTOMap.computeIfAbsent(usuario.getId(), k -> new UsuarioDTO(
                    usuario.getId(),
                    usuario.getName(),
                    usuario.getEmail(),
                    usuario.getDni(), usuario.getProfiles().stream().map(perfiles -> perfiles.getName()).findFirst().orElse("")
                    )

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

    @Override
    public List<ComisionConUsuarioDTO> getComisionesByid(Integer id) {
        List<ComisionConUsuarioDTO> resultado = new ArrayList<>();
        Usuarios usuarioOptional = commonUsuarioService.verifyUsuarioExistsById(id);
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
                usuarioOptional.getId(),
                usuarioOptional.getName(),
                usuarioOptional.getEmail(),
                usuarioOptional.getDni(),
                usuarioOptional.getProfiles().stream().map(Perfiles::getName).findFirst().orElse("")
        );

        resultado.add(new ComisionConUsuarioDTO(
                usuarioOptional.getId(),
                usuarioDTO,
                comisionesDTOs
        ));

        return resultado;
    }

    @Override
    public Comisiones verifyComisionExistsById(Integer id) {
        return comisionRepository.findById(id).orElseThrow(() ->
                new ComisionNotFoundException("No se encontró la comisión"));
    }

    @Override
    @Transactional
    public void saveComisiones(Integer id, List<ComisionRequest> comisionRequests) {
        Usuarios usuario = commonUsuarioService.verifyUsuarioExistsById(id);
        for (ComisionRequest comisionRequest : comisionRequests) {
            Comisiones comision = new Comisiones();
            comision.setPorcentaje(comisionRequest.getPorcentaje());
            comision.setMontomax(comisionRequest.getMontomax());
            comision.setUsuarios(usuario);
            comisionRepository.save(comision);
        }
    }

    @Override
    @Transactional
    public void updateComisiones(Integer id, List<ComisionRequest> comisionRequests) {
        List<Comisiones> comisionesList = comisionRepository.getComisionbyusuario(id);
        if (comisionesList.isEmpty()) {
            throw new ComisionNotFoundException("No se encontrarón comisiones para el usuario");
        }
        Map<Integer, Comisiones> comisionesMap = comisionesList.stream()
                .collect(Collectors.toMap(Comisiones::getId, comision -> comision));
        for (ComisionRequest comisionRequest : comisionRequests) {
            Comisiones comision = comisionesMap.get(comisionRequest.getId());
            if (comision != null) {
                comision.setPorcentaje(comisionRequest.getPorcentaje());
                comision.setMontomax(comisionRequest.getMontomax());
                comisionRepository.save(comision);
            } else {
                throw new ComisionNotFoundException("No se encontró la comisión con ID: " + comisionRequest.getId());
            }
        }
    }

    @Override
    public void deleteComisiones(Integer id) {
        Comisiones comisiones = verifyComisionExistsById(id);
        comisiones.setStatus(false);
        comisionRepository.save(comisiones);
    }
}
