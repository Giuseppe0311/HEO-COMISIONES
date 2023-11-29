package com.pe.HeoComisiones.Services.impl.user;

import com.pe.HeoComisiones.Entity.DetalleComisiones;
import com.pe.HeoComisiones.Repository.DetalleComisionesRepository;
import com.pe.HeoComisiones.Request.DetallecoRequest;
import com.pe.HeoComisiones.Services.common.CommonDetalleComisionService;
import com.pe.HeoComisiones.Services.common.CommonResultTrabajadoresService;
import com.pe.HeoComisiones.Services.common.CommonUsuarioService;
import com.pe.HeoComisiones.Services.user.UserDetalleComisionesService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;


@RequiredArgsConstructor
@Service
public class UserDetalleComisionesServiceImpl implements UserDetalleComisionesService {
    private final CommonUsuarioService commonUsuarioService;
    private final CommonDetalleComisionService commonDetalleComisionService;
    private final CommonResultTrabajadoresService commonResultTrabajadoresService;
    private final DetalleComisionesRepository detalleComisionesRepository;
    @Override
    @Transactional
    public Integer saveDetalleComisiones(DetallecoRequest detallecoRequest) {
        DetalleComisiones detalleComisiones = new DetalleComisiones();
        detalleComisiones.setResultTrabajadores(commonResultTrabajadoresService.verifyResultTrabajadoresExistsById(detallecoRequest.getResultTrabajadores()));
        detalleComisiones.setUsuarios(commonUsuarioService.verifyUsuarioExistsById(detallecoRequest.getUsuarios()));
        detalleComisionesRepository.save(detalleComisiones);
        return detalleComisiones.getId();
    }
}
