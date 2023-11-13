package com.pe.HeoComisiones.Mappers;

import com.pe.HeoComisiones.DTOs.*;
import com.pe.HeoComisiones.Entity.DetalleComisiones;
import org.springframework.stereotype.Service;

import java.util.function.Function;

@Service
public class DetalleComisionDTOMapper implements Function<DetalleComisiones, DetalleComisionDTO> {

    @Override
    public DetalleComisionDTO apply(DetalleComisiones detalleComisiones) {
        ClienteDTO cliente = new ClienteDTO(
                detalleComisiones.getInversor().getClientes().getId(),
                detalleComisiones.getInversor().getClientes().getNombre(),
                detalleComisiones.getInversor().getClientes().getApellido(),
                detalleComisiones.getInversor().getClientes().getDni(),
                detalleComisiones.getInversor().getClientes().getTelefono(),
                detalleComisiones.getInversor().getClientes().getDistrito(),
                detalleComisiones.getInversor().getClientes().getProvincia(),
                detalleComisiones.getInversor().getClientes().getDepartamento()
        );
        InversorDTO inversor = new InversorDTO(
                detalleComisiones.getInversor().getId(),
                cliente,
                detalleComisiones.getInversor().getMontoinvertido(),
                detalleComisiones.getInversor().getContrato(),
                detalleComisiones.getInversor().isStatus()
        );
        ResulTrabajadoresDTO resulTrabajadores = new ResulTrabajadoresDTO(
                detalleComisiones.getResultTrabajadores().getId(),
                detalleComisiones.getResultTrabajadores().getComisionTotal(),
                detalleComisiones.getResultTrabajadores().getMontototal(),
                detalleComisiones.getResultTrabajadores().getGanancia()
        );
        UsuarioDTO usuarioDTO = new UsuarioDTO(
                detalleComisiones.getUsuarios().getId(),
                detalleComisiones.getUsuarios().getName(),
                detalleComisiones.getUsuarios().getEmail(),
                detalleComisiones.getUsuarios().getDni()
        );
        return new DetalleComisionDTO(
                detalleComisiones.getId(),
                inversor,
                resulTrabajadores,
                detalleComisiones.getMescomercial(),
                usuarioDTO
        );
    }
}
