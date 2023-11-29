package com.pe.HeoComisiones.Services.common;

import com.pe.HeoComisiones.Entity.Usuarios;

public interface CommonUsuarioService {
    Usuarios verifyUsuarioExistsById(Integer id);
}
