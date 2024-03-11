package com.pe.HeoComisiones.Controller.admin;

import com.pe.HeoComisiones.DTOs.ComisionesDTO;
import com.pe.HeoComisiones.DTOs.admin.ComisionConUsuarioDTO;
import com.pe.HeoComisiones.Request.ComisionRequest;
import com.pe.HeoComisiones.Services.admin.AdminComisionesService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@PreAuthorize("hasAnyRole('ADMIN','SUPERVISOR')")
@RequestMapping("/admin/comisiones")
public class ComisionController {
    private final AdminComisionesService comisionService;

    @GetMapping
    public ResponseEntity<List<ComisionConUsuarioDTO>> getComision() {
        return ResponseEntity.ok(comisionService.obtenerComisionesConUsuarios());
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getComisionById(@PathVariable Integer id) {
        return ResponseEntity.ok(comisionService.getComisionesByid(id));

    }

    @PostMapping("/{id}")
    public ResponseEntity<?> saveComision(@PathVariable Integer id, @RequestBody List<ComisionRequest> comisionRequest) {
        comisionService.saveComisiones(id, comisionRequest);
        return ResponseEntity.ok("Comision guardada");
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateComision(@PathVariable Integer id, @RequestBody List<ComisionRequest> comisionRequest) {
        comisionService.updateComisiones(id, comisionRequest);
        return ResponseEntity.ok("Comision actualizada");

    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteComision(@PathVariable Integer id) {
        comisionService.deleteComisiones(id);
        return ResponseEntity.ok("Comision eliminada");
    }
}
