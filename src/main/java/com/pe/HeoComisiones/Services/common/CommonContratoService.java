package com.pe.HeoComisiones.Services.common;

import com.pe.HeoComisiones.DTOs.ContratoValuesRequest;

import java.io.IOException;
import java.util.Map;

public interface CommonContratoService {
    Map<String,Object> generateContrato(ContratoValuesRequest contratoValuesRequest) throws IOException, InterruptedException;
}
