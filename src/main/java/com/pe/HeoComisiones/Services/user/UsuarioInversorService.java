package com.pe.HeoComisiones.Services.user;

import com.pe.HeoComisiones.DTOs.InversorDTO;
import com.pe.HeoComisiones.Request.InversorRequest;
import com.pe.HeoComisiones.Request.InversorUsuarioDetalleRequest;

import java.util.List;

public interface UsuarioInversorService {
    void saveInversor(InversorRequest inversorRequest);
    List<InversorDTO> getInversoresbyUsuario(Integer id);
}
