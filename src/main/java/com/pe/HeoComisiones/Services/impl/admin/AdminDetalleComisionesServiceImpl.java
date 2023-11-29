package com.pe.HeoComisiones.Services.impl.admin;

import com.pe.HeoComisiones.DTOs.DetalleComisionDTO;
import com.pe.HeoComisiones.Entity.DetalleComisiones;
import com.pe.HeoComisiones.Exception.DetalleComisionNotFound;
import com.pe.HeoComisiones.Mappers.DetalleComisionDTOMapper;
import com.pe.HeoComisiones.Repository.DetalleComisionesRepository;
import com.pe.HeoComisiones.Request.DetallecoRequest;
import com.pe.HeoComisiones.Services.admin.AdminDetalleComisionesService;
import com.pe.HeoComisiones.Services.common.CommonDetalleComisionService;
import com.pe.HeoComisiones.Services.common.CommonResultTrabajadoresService;
import com.pe.HeoComisiones.Services.common.CommonUsuarioService;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class AdminDetalleComisionesServiceImpl implements AdminDetalleComisionesService , CommonDetalleComisionService {
    private final DetalleComisionesRepository detalleComisionesRepository;
    private final DetalleComisionDTOMapper detalleComisionDTOMapper;
    private final CommonResultTrabajadoresService resultTrabajadoresService;
   private final CommonUsuarioService commonUsuarioService;


    @Override
    public List<DetalleComisionDTO> getallDetalles() {
        Sort sort  = Sort.by(Sort.Direction.DESC,"id");
        return detalleComisionesRepository.findAll(sort).
                stream()
                .map(detalleComisionDTOMapper)
                .collect(Collectors.toList());
    }

    @Override
    public DetalleComisionDTO getdetallesByid(Integer id) {
        DetalleComisiones detalleComisionesOptional = verifyDetalleComisionExistsById(id);
        return detalleComisionDTOMapper.apply(detalleComisionesOptional);
    }

    @Override
    public List<DetalleComisionDTO> getdetallebyusuario(Integer idusuario) {
        return detalleComisionesRepository.getdetallebyusuario(idusuario)
                .stream()
                .map(detalleComisionDTOMapper)
                .collect(Collectors.toList());
    }

    @Override
    @Transactional
    public void updateDetalle(Integer id, DetallecoRequest detallecoRequest) {
        DetalleComisiones detalleComisiones = detalleComisionesRepository.findById(id).orElse(null);
        if (detalleComisiones != null){
            detalleComisiones.setResultTrabajadores(resultTrabajadoresService.verifyResultTrabajadoresExistsById(detallecoRequest.getResultTrabajadores()));
            detalleComisiones.setUsuarios(commonUsuarioService.verifyUsuarioExistsById(detallecoRequest.getUsuarios()));
            detalleComisionesRepository.save(detalleComisiones);
        }
        throw new DetalleComisionNotFound("No existe el detalle");
    }

    @Override
    public DetalleComisiones verifyDetalleComisionExistsById(Integer id) {
        return detalleComisionesRepository.findById(id).orElseThrow(
                () -> new DetalleComisionNotFound("No se encontró el detalle")
        );
    }
}
