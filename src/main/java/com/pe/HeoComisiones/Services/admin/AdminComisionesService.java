package com.pe.HeoComisiones.Services.admin;

import com.pe.HeoComisiones.DTOs.admin.ComisionConUsuarioDTO;
import com.pe.HeoComisiones.Entity.Comisiones;
import com.pe.HeoComisiones.Request.ComisionRequest;

import java.util.List;

public interface AdminComisionesService {
    List<ComisionConUsuarioDTO> obtenerComisionesConUsuarios();
    List<ComisionConUsuarioDTO> getComisionesByid(Integer id);
    Comisiones verifyComisionExistsById(Integer id);
    void saveComisiones(Integer id, List<ComisionRequest> comisionRequests);
    void updateComisiones(Integer id, List<ComisionRequest> comisionRequests);
    void deleteComisiones(Integer id);
}
