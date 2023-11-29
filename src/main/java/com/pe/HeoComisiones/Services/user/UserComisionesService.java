package com.pe.HeoComisiones.Services.user;

import com.pe.HeoComisiones.DTOs.ComisionesDTO;

import java.util.List;

public interface UserComisionesService {
    List<ComisionesDTO> getComisionesByUsuario(Integer id);
}
