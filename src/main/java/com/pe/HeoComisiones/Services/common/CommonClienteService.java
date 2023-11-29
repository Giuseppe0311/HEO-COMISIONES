package com.pe.HeoComisiones.Services.common;

import com.pe.HeoComisiones.DTOs.ClienteDTO;
import com.pe.HeoComisiones.Entity.Clientes;
import java.util.List;

public interface CommonClienteService {
    Clientes verifyClienteExistsById(Integer id);
}
