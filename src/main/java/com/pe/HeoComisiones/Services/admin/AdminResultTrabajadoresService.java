package com.pe.HeoComisiones.Services.admin;

import com.pe.HeoComisiones.DTOs.ResulTrabajadoresDTO;
import com.pe.HeoComisiones.Entity.ResultTrabajadores;
import com.pe.HeoComisiones.Request.ResultTrabajadoresRequest;

import java.util.List;

public interface AdminResultTrabajadoresService {
    List<ResulTrabajadoresDTO> getResultTrabajadores();
    ResulTrabajadoresDTO getResultTrabajadoresByid(Integer id);
    ResultTrabajadores updateResultTrabajadores(Integer id, ResultTrabajadoresRequest resultTrabajadoresRequest);
}
