package com.pe.HeoComisiones.Mappers;

import com.pe.HeoComisiones.DTOs.ResulTrabajadoresDTO;
import com.pe.HeoComisiones.Entity.ResultTrabajadores;
import org.springframework.stereotype.Service;

import java.util.function.Function;

@Service
public class ResultTrabajadoresDTOMapper implements Function<ResultTrabajadores, ResulTrabajadoresDTO> {
    @Override
    public ResulTrabajadoresDTO apply(ResultTrabajadores resultTrabajadores) {
        return new ResulTrabajadoresDTO(
                resultTrabajadores.getId(),
                resultTrabajadores.getComisionTotal(),
                resultTrabajadores.getMontototal(),
                resultTrabajadores.getGanancia());
    }
}
