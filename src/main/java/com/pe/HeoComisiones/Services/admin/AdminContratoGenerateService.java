package com.pe.HeoComisiones.Services.admin;

import com.pe.HeoComisiones.DTOs.ContratoValuesRequest;

import java.io.IOException;
import java.util.Map;

public interface AdminContratoGenerateService {
    Map<String,Object> generateContrato(ContratoValuesRequest contratoValuesRequest) throws IOException, InterruptedException;

}
