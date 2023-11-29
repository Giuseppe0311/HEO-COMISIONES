package com.pe.HeoComisiones.Services.impl.admin;

import com.pe.HeoComisiones.DTOs.ResulTrabajadoresDTO;
import com.pe.HeoComisiones.Entity.ResultTrabajadores;
import com.pe.HeoComisiones.Exception.ResultTrabajadoresNotFoundException;
import com.pe.HeoComisiones.Mappers.ResultTrabajadoresDTOMapper;
import com.pe.HeoComisiones.Repository.ResultTrabajadoresRepository;
import com.pe.HeoComisiones.Request.ResultTrabajadoresRequest;
import com.pe.HeoComisiones.Services.admin.AdminResultTrabajadoresService;
import com.pe.HeoComisiones.Services.common.CommonResultTrabajadoresService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class AdminResultTrabajadoresServiceImpl implements AdminResultTrabajadoresService , CommonResultTrabajadoresService {

        private final ResultTrabajadoresRepository resultTrabajadoresRepository;
        private final ResultTrabajadoresDTOMapper resulTrabajadoresDTOMapper;

    @Override
    public List<ResulTrabajadoresDTO> getResultTrabajadores() {
        return resultTrabajadoresRepository.findAll()
                .stream()
                .map(resulTrabajadoresDTOMapper)
                .collect(Collectors.toList());
    }

    @Override
    public ResulTrabajadoresDTO getResultTrabajadoresByid(Integer id)  {
        ResultTrabajadores result = verifyResultTrabajadoresExistsById(id);
        return resulTrabajadoresDTOMapper.apply(result);
    }
    @Override
    public ResultTrabajadores updateResultTrabajadores(Integer id, ResultTrabajadoresRequest resultTrabajadoresRequest) {
        ResultTrabajadores result = verifyResultTrabajadoresExistsById(id);
        result.setComisionTotal(resultTrabajadoresRequest.getComisionTotal());
        result.setMontototal(resultTrabajadoresRequest.getMontototal());
        result.setGanancia(resultTrabajadoresRequest.getGanancia());
        return resultTrabajadoresRepository.save(result);

    }

    @Override
    public ResultTrabajadores verifyResultTrabajadoresExistsById(Integer id) {
        return resultTrabajadoresRepository.findById(id)
                .orElseThrow(() -> new ResultTrabajadoresNotFoundException("No se encontró el resultado del trabajador"));
    }
}
