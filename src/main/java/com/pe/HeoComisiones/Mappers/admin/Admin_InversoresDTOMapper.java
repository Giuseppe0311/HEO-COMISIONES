package com.pe.HeoComisiones.Mappers.admin;

import com.pe.HeoComisiones.DTOs.ClienteDTO;
import com.pe.HeoComisiones.DTOs.UsuarioDTO;
import com.pe.HeoComisiones.DTOs.admin.Admin_InversoresDTO;
import com.pe.HeoComisiones.Entity.Inversor;
import com.pe.HeoComisiones.Entity.Perfiles;
import org.springframework.stereotype.Service;

import java.util.function.Function;

@Service
public class Admin_InversoresDTOMapper implements Function<Inversor, Admin_InversoresDTO> {
    @Override
    public Admin_InversoresDTO apply(Inversor inversor) {
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
        UsuarioDTO usuarios = new UsuarioDTO(
                inversor.getUsuarios().getId(),
                inversor.getUsuarios().getName(),
                inversor.getUsuarios().getEmail(),
                inversor.getUsuarios().getDni(),
                inversor.getUsuarios().getProfiles().stream().map(Perfiles::getName).findFirst().orElse("")
        );

        return new Admin_InversoresDTO(
                inversor.getId(),
                clientes,
                usuarios,
                inversor.getMontoinvertido(),
                inversor.getContrato(),
                inversor.isStatus(),
                inversor.getCreadopor(),
                inversor.getFechacreacion(),
                inversor.getUltimaactualizacionpor(),
                inversor.getFechaultimaactualizacion()
        );
    }
}
