package com.pe.HeoComisiones.Controller.admin;

import com.pe.HeoComisiones.Request.DetallecoRequest;
import com.pe.HeoComisiones.Services.admin.AdminDetalleComisionesService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@PreAuthorize("hasRole('ADMIN')")
@RequestMapping("/admin/detallecomisiones")
public class DetalleComisionesController {
   private final AdminDetalleComisionesService detalleComisionesService;
    @PreAuthorize("hasAnyRole('ADMIN','SUPERVISOR')")
    @GetMapping
    public ResponseEntity<?> getallcomisiones(){
            return ResponseEntity.ok(detalleComisionesService.getallDetalles());
    }
    @PreAuthorize("hasAnyRole('ADMIN','SUPERVISOR')")
    @GetMapping("/{id}")
    public ResponseEntity<?> getcomisionesbyid(@PathVariable Integer id){
        return ResponseEntity.ok(detalleComisionesService.getdetallesByid(id));

    }
    @PutMapping("/{id}")
    public ResponseEntity<?> updatecomisiones(@PathVariable Integer id,@RequestBody DetallecoRequest detallecoRequest){
            detalleComisionesService.updateDetalle(id,detallecoRequest);
            return ResponseEntity.ok("Detalle de comisiones actualizado");
    }
}
