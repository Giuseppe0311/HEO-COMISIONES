package com.pe.HeoComisiones.Services.admin;

import com.pe.HeoComisiones.DTOs.DetalleComisionDTO;
import com.pe.HeoComisiones.Entity.DetalleComisiones;
import com.pe.HeoComisiones.Request.DetallecoRequest;

import java.util.List;

public interface AdminDetalleComisionesService {

    List<DetalleComisionDTO> getallDetalles();
    DetalleComisionDTO getdetallesByid(Integer id);
    void updateDetalle(Integer id, DetallecoRequest detallecoRequest);

}
