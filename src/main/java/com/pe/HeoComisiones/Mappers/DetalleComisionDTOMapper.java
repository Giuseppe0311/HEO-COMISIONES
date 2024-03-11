package com.pe.HeoComisiones.Mappers;

import com.pe.HeoComisiones.DTOs.*;
import com.pe.HeoComisiones.Entity.DetalleComisiones;
import com.pe.HeoComisiones.Entity.Perfiles;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

@Service
public class DetalleComisionDTOMapper implements Function<DetalleComisiones, DetalleComisionDTO> {

    @Override
    public DetalleComisionDTO apply(DetalleComisiones detalleComisiones) {
      List<InversorDTO> inversoresDTO = detalleComisiones.getInversores().stream()
              .map(inversor -> new InversorDTO(
                      inversor.getId(),
                      new ClienteDTO(
                              inversor.getClientes().getId(),
                              inversor.getClientes().getNombre(),
                              inversor.getClientes().getApellido(),
                              inversor.getClientes().getDni(),
                              inversor.getClientes().getTelefono(),
                              inversor.getClientes().getDistrito(),
                              inversor.getClientes().getProvincia(),
                              inversor.getClientes().getDepartamento(),
                              inversor.getClientes().getCorreoelectronico()
                      ),
                      inversor.getMontoinvertido(),
                      inversor.getContrato(),
                      inversor.isStatus()
              )).toList();
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
                detalleComisiones.getUsuarios().getDni(),
                detalleComisiones.getUsuarios().getProfiles().stream().map(Perfiles::getName).findFirst().orElse("")
        );
        return new DetalleComisionDTO(
                detalleComisiones.getId(),
                inversoresDTO,
                resulTrabajadores,
                usuarioDTO,
                detalleComisiones.getCreadopor(),
                detalleComisiones.getFechacreacion(),
                detalleComisiones.getUltimaactualizacionpor(),
                detalleComisiones.getFechaultimaactualizacion()
        );
    }
}
