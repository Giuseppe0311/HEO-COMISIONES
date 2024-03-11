package com.pe.HeoComisiones.Controller.admin;

import com.pe.HeoComisiones.DTOs.admin.AdminConsultas_InversoresDTO;
import com.pe.HeoComisiones.DTOs.admin.Admin_InversoresDTO;
import com.pe.HeoComisiones.Request.InversorRequest;
import com.pe.HeoComisiones.Services.admin.AdminInversorService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/admin/inversores")
@PreAuthorize("hasRole('ADMIN')")
public class InversionController {
    private final AdminInversorService adminInversorService;
    @PreAuthorize("hasAnyRole('ADMIN','SUPERVISOR')")
    @GetMapping
    public ResponseEntity<List<Admin_InversoresDTO>> getInversor() {
        return ResponseEntity.ok(adminInversorService.getInversores());
    }
    @PreAuthorize("hasAnyRole('ADMIN','SUPERVISOR')")
    @GetMapping("/avances")
    public ResponseEntity<List<AdminConsultas_InversoresDTO>> getInversorAvances() {
        return ResponseEntity.ok(adminInversorService.getInversoresAvances());
    }
    @PreAuthorize("hasAnyRole('ADMIN','SUPERVISOR')")
    @GetMapping("/{id}")
    public ResponseEntity<Admin_InversoresDTO> getInversorbyId(@PathVariable Integer id) {
        return ResponseEntity.ok(adminInversorService.getInversorbyId(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateInversor(@PathVariable Integer id, @RequestBody InversorRequest inversorRequest) {
        adminInversorService.updateInversor(id, inversorRequest);
       return ResponseEntity.ok("Inversor actualizado");
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteInversor(@PathVariable Integer id) {
            adminInversorService.deleteInversor(id);
            return ResponseEntity.ok("Inversor eliminado");
    }
}
