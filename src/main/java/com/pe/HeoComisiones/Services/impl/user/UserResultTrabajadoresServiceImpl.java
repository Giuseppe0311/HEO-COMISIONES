package com.pe.HeoComisiones.Services.impl.user;

import com.pe.HeoComisiones.Entity.ResultTrabajadores;
import com.pe.HeoComisiones.Mappers.ResultTrabajadoresDTOMapper;
import com.pe.HeoComisiones.Repository.ResultTrabajadoresRepository;
import com.pe.HeoComisiones.Request.ResultTrabajadoresRequest;
import com.pe.HeoComisiones.Services.common.CommonUsuarioService;
import com.pe.HeoComisiones.Services.user.UserResultTrabajadoresService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserResultTrabajadoresServiceImpl  implements UserResultTrabajadoresService {
    private final CommonUsuarioService commonUsuarioService;
    private final ResultTrabajadoresRepository resultTrabajadoresRepository;
    @Override
    @Transactional
    public Integer saveResult(ResultTrabajadoresRequest resultTrabajadoresRequest) {
        ResultTrabajadores resultTrabajadores = new ResultTrabajadores();
        resultTrabajadores.setComisionTotal(resultTrabajadoresRequest.getComisionTotal());
        resultTrabajadores.setMontototal(resultTrabajadoresRequest.getMontototal());
        resultTrabajadores.setGanancia(resultTrabajadoresRequest.getGanancia());
        resultTrabajadores.setUsuarios(commonUsuarioService.verifyUsuarioExistsById(resultTrabajadoresRequest.getIdusuario()));
        resultTrabajadoresRepository.save(resultTrabajadores);
        return resultTrabajadores.getId();
    }
}
