package com.pe.HeoComisiones.Services.common;

import com.pe.HeoComisiones.DTOs.DetalleComisionDTO;
import com.pe.HeoComisiones.Entity.DetalleComisiones;

import java.util.List;

public interface CommonDetalleComisionService {

     DetalleComisiones verifyDetalleComisionExistsById(Integer id);
     List<DetalleComisionDTO> getdetallebyusuario(Integer idusuario);

}
