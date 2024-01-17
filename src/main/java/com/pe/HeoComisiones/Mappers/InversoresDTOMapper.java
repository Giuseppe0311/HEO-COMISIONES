package com.pe.HeoComisiones.Mappers;

import com.pe.HeoComisiones.DTOs.ClienteDTO;
import com.pe.HeoComisiones.DTOs.InversorDTO;
import com.pe.HeoComisiones.DTOs.UsuarioDTO;
import com.pe.HeoComisiones.Entity.Inversor;
import org.springframework.stereotype.Service;

import java.util.function.Function;
@Service
public class InversoresDTOMapper implements Function<Inversor, InversorDTO> {

    @Override
    public InversorDTO apply(Inversor inversor) {
        ClienteDTO clientes = new ClienteDTO(
                inversor.getClientes().getId(),
                inversor.getClientes().getNombre(),
                inversor.getClientes().getApellido(),
                inversor.getClientes().getDni(),
                inversor.getClientes().getTelefono(),
                inversor.getClientes().getDistrito(),
                inversor.getClientes().getProvincia(),
                inversor.getClientes().getDepartamento(),
                inversor.getClientes().getCorreoelectronico()
        );

        return new InversorDTO(
                inversor.getId(),
                clientes,
                inversor.getMontoinvertido(),
                inversor.getContrato(),
                inversor.isStatus()
        );
    }
}
