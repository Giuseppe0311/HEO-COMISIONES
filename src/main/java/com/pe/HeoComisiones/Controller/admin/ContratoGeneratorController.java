package com.pe.HeoComisiones.Controller.admin;

import com.pe.HeoComisiones.DTOs.ContratoValuesRequest;
import com.pe.HeoComisiones.Services.common.CommonContratoService;
import com.pe.HeoComisiones.Validations.ValidationUtils;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.Resource;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.Map;

@RestController
@RequestMapping("/admin/contrato-generator")
@RequiredArgsConstructor
public class ContratoGeneratorController {
    // TODO: 2/01/2024 FALTA TERMINAR ESTE CONTROLADOR Y HACER LAS PRUEBAS UNITARIAS
    private final CommonContratoService commonContratoService;
    //ruta para obtener el contrato
    @PostMapping("/generate")
    public ResponseEntity<?> generateContrato(@RequestBody ContratoValuesRequest contratoValuesRequest) throws IOException, InterruptedException {
       try {
           Map<String, Object> bytescontrato_nombrecontrato = commonContratoService.generateContrato(contratoValuesRequest);
           ByteArrayResource recurso = new ByteArrayResource((byte[]) bytescontrato_nombrecontrato.get("fileBytes"));
           return ResponseEntity.ok().
                    header("Content-Disposition", "attachment; filename=\"" + bytescontrato_nombrecontrato.get("fileName").toString() + "\"")
                   .header("Content-Type", "application/vnd.openxmlformats-officedocument.wordprocessingml.document")
                   .body(recurso);
       }catch (Exception e) {
           return ResponseEntity.internalServerError().body(e.toString());
       }

    }
}
