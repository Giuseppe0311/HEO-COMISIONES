package com.pe.HeoComisiones.Mappers.admin;

import com.pe.HeoComisiones.DTOs.admin.AdminContratosDTO;
import com.pe.HeoComisiones.Entity.Contratos;
import org.springframework.stereotype.Service;

import java.util.function.Function;

@Service
public class AdminContratosDTOMapper implements Function<Contratos, AdminContratosDTO> {

    @Override
    public AdminContratosDTO apply(Contratos contratos) {
        return new AdminContratosDTO(
                contratos.getIdcontrato(),
                contratos.getCodigoContrato(),
                contratos.getUrlContrato(),
                contratos.getFechaCreacion(),
                contratos.getTipoContrato(),
                contratos.getNombre_cliente(),
                contratos.getDni_cliente(),
                contratos.getTipo_documento_cliente(),
                contratos.getTipo_moneda()
        );
    }
}
