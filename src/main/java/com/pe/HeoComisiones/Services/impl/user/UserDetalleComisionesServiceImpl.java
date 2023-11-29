package com.pe.HeoComisiones.Services.impl.user;

import com.pe.HeoComisiones.Entity.DetalleComisiones;
import com.pe.HeoComisiones.Entity.Inversor;
import com.pe.HeoComisiones.Entity.ResultTrabajadores;
import com.pe.HeoComisiones.Entity.Usuarios;
import com.pe.HeoComisiones.Exception.InversorNotFoundException;
import com.pe.HeoComisiones.Repository.DetalleComisionesRepository;
import com.pe.HeoComisiones.Repository.InversorRepository;
import com.pe.HeoComisiones.Repository.ResultTrabajadoresRepository;
import com.pe.HeoComisiones.Request.ResultTrabajadoresRequest;
import com.pe.HeoComisiones.Services.common.CommonDetalleComisionService;
import com.pe.HeoComisiones.Services.common.CommonResultTrabajadoresService;
import com.pe.HeoComisiones.Services.common.CommonUsuarioService;
import com.pe.HeoComisiones.Services.user.UserDetalleComisionesService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;


@RequiredArgsConstructor
@Service
public class UserDetalleComisionesServiceImpl implements UserDetalleComisionesService {
    private final CommonUsuarioService commonUsuarioService;
    private final DetalleComisionesRepository detalleComisionesRepository;
    private final ResultTrabajadoresRepository resultTrabajadoresRepository;
    private final InversorRepository inversorRepository;
    @Override
    @Transactional
    public void saveDetalleComisiones(Integer id, ResultTrabajadoresRequest resultTrabajadoresRequest) {
        Usuarios usuarios  = commonUsuarioService.verifyUsuarioExistsById(id);
        List<Integer> inversores = inversorRepository.getInversorIdsByUsuario(id);
        if (inversores.isEmpty()){
            throw new InversorNotFoundException("No se encontrarón inversores");
        }
        ResultTrabajadores resultTrabajadores = new ResultTrabajadores();
        resultTrabajadores.setComisionTotal(resultTrabajadoresRequest.getComisionTotal());
        resultTrabajadores.setMontototal(resultTrabajadoresRequest.getMontototal());
        resultTrabajadores.setGanancia(resultTrabajadoresRequest.getGanancia());
        resultTrabajadores.setUsuarios(usuarios);
        resultTrabajadoresRepository.save(resultTrabajadores);
        DetalleComisiones detalleComisiones = new DetalleComisiones();
        detalleComisiones.setResultTrabajadores(resultTrabajadores);
        detalleComisiones.setUsuarios(usuarios);
        detalleComisionesRepository.save(detalleComisiones);
        inversorRepository.updateInversoresforDetalleComisiones(detalleComisiones,inversores);
    }
}
