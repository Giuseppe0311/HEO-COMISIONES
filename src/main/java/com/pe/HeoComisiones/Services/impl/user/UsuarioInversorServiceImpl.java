package com.pe.HeoComisiones.Services.impl.user;

import com.pe.HeoComisiones.DTOs.InversorDTO;
import com.pe.HeoComisiones.Entity.DetalleComisiones;
import com.pe.HeoComisiones.Entity.Inversor;
import com.pe.HeoComisiones.Exception.DetalleComisionNotFound;
import com.pe.HeoComisiones.Exception.InversorNotFoundException;
import com.pe.HeoComisiones.Mappers.InversoresDTOMapper;
import com.pe.HeoComisiones.Repository.InversorRepository;
import com.pe.HeoComisiones.Request.InversorRequest;
import com.pe.HeoComisiones.Request.InversorUsuarioDetalleRequest;
import com.pe.HeoComisiones.Services.common.CommonClienteService;
import com.pe.HeoComisiones.Services.common.CommonDetalleComisionService;
import com.pe.HeoComisiones.Services.common.CommonUsuarioService;
import com.pe.HeoComisiones.Services.user.UsuarioInversorService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;


@Service
@RequiredArgsConstructor
public class UsuarioInversorServiceImpl implements UsuarioInversorService {
    private final CommonClienteService commonClienteService;
    private final CommonUsuarioService commonUsuarioService;
    private final CommonDetalleComisionService commonDetalleComisionService;
    private final InversorRepository inversorRepository;
    private final InversoresDTOMapper inversoresDTOMapper;
    @Override
    public void saveInversor(InversorRequest inversorRequest) {
        Inversor inversor = new Inversor();
        inversor.setStatus(true);
        inversor.setClientes(commonClienteService.verifyClienteExistsById(inversorRequest.getIdcliente()));
        inversor.setContrato(inversorRequest.getContrato());
        inversor.setUsuarios(commonUsuarioService.verifyUsuarioExistsById(inversorRequest.getIdusuario()));
        inversor.setDetalleComisiones(null);
        inversor.setMontoinvertido(inversorRequest.getMontoinvertido());
        inversorRepository.save(inversor);
    }

    @Override
    public List<InversorDTO> getInversoresbyUsuario(Integer id) {
        return inversorRepository.getInversionesByusuario(id)
                .stream()
                .map(inversoresDTOMapper)
                .collect(Collectors.toList());
    }

}
