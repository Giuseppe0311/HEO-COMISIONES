package com.pe.HeoComisiones.Services.common;

import com.pe.HeoComisiones.Entity.ResultTrabajadores;
import com.pe.HeoComisiones.Request.ResultTrabajadoresRequest;

public interface CommonResultTrabajadoresService {
    ResultTrabajadores verifyResultTrabajadoresExistsById(Integer id);
}
