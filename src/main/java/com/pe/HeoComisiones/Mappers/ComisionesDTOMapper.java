package com.pe.HeoComisiones.Mappers;

import com.pe.HeoComisiones.DTOs.ComisionesDTO;
import com.pe.HeoComisiones.Entity.Comisiones;
import org.springframework.stereotype.Service;

import java.util.function.Function;
@Service
public class ComisionesDTOMapper implements Function<Comisiones, ComisionesDTO> {
    @Override
    public ComisionesDTO apply(Comisiones comisiones) {
        return new ComisionesDTO(
                comisiones.getId(),
                comisiones.getPorcentaje(),
                comisiones.getMontomax(),
                comisiones.isStatus()
        );

    }
}
