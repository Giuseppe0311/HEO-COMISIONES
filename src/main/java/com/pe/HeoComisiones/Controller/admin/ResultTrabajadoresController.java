package com.pe.HeoComisiones.Controller.admin;

import com.pe.HeoComisiones.DTOs.ResulTrabajadoresDTO;
import com.pe.HeoComisiones.Request.ResultTrabajadoresRequest;
import com.pe.HeoComisiones.Services.admin.AdminResultTrabajadoresService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/admin/resulttrabajadores")
@PreAuthorize("hasRole('ADMIN')")
@RequiredArgsConstructor
public class ResultTrabajadoresController {

    private final AdminResultTrabajadoresService resultTrabajadoresService;

    @PreAuthorize("hasAnyRole('ADMIN','SUPERVISOR')")
    @GetMapping
    public ResponseEntity<List<ResulTrabajadoresDTO>> getresult() {
        return ResponseEntity.ok(resultTrabajadoresService.getResultTrabajadores());
    }

    @PreAuthorize("hasAnyRole('ADMIN','SUPERVISOR')")
    @GetMapping("/{id}")
    public ResponseEntity<?> getresultbyid(@PathVariable Integer id) {
        return ResponseEntity.ok(resultTrabajadoresService.getResultTrabajadoresByid(id));
    }

    @PutMapping
    public ResponseEntity<?> updateresult(@PathVariable Integer id, @RequestBody ResultTrabajadoresRequest resultTrabajadoresRequest) {
        return ResponseEntity.ok(resultTrabajadoresService.updateResultTrabajadores(id, resultTrabajadoresRequest));
    }
}
