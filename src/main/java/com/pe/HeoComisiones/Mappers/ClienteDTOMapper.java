package com.pe.HeoComisiones.Mappers;

import com.pe.HeoComisiones.DTOs.ClienteDTO;
import com.pe.HeoComisiones.Entity.Clientes;
import org.springframework.stereotype.Service;

import java.util.function.Function;

@Service
public class ClienteDTOMapper implements Function<Clientes, ClienteDTO> {
    @Override
    public ClienteDTO apply(Clientes clientes) {
        return new ClienteDTO(
                clientes.getId(),
                clientes.getNombre(),
                clientes.getApellido(),
                clientes.getDni(),
                clientes.getTelefono(),
                clientes.getDistrito(),
                clientes.getProvincia(),
                clientes.getDepartamento(),
                clientes.getCorreoelectronico()
        );
    }
}
