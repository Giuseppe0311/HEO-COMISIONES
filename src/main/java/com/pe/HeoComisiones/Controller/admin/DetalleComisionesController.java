package com.pe.HeoComisiones.Controller.admin;

import com.pe.HeoComisiones.Entity.DetalleComisiones;
import com.pe.HeoComisiones.Request.DetallecoRequest;
import com.pe.HeoComisiones.Services.DetalleComisionesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("admin/detallecomisiones")
public class DetalleComisionesController {
    @Autowired
    private DetalleComisionesService detalleComisionesService;

    @GetMapping
    public ResponseEntity<?> getallcomisiones(){
        try {
            return ResponseEntity.ok(detalleComisionesService.getallDetalles());
        }catch (Exception e){
            return ResponseEntity.badRequest().body("Error");
        }

    }
    @GetMapping("/{id}")
    public ResponseEntity<?> getcomisionesbyid(@PathVariable Integer id){
        try {
            return ResponseEntity.ok(detalleComisionesService.getdetallesByid(id));
        }catch (Exception e){
            return ResponseEntity.badRequest().body(e.getMessage());
        }

    }
    @PostMapping
    public ResponseEntity<?> savecomisiones(@RequestBody DetallecoRequest detallecoRequest){
        try {
            detalleComisionesService.saveDetalle(detallecoRequest);
            return ResponseEntity.ok("Detalle de comisiones guardado");
        }catch (Exception e){
            return ResponseEntity.badRequest().body(e.getMessage());
        }

    }
    @PutMapping("/{id}")
    public ResponseEntity<?> updatecomisiones(@PathVariable Integer id,@RequestBody DetallecoRequest detallecoRequest){
        try {
            detalleComisionesService.updateDetalle(id,detallecoRequest);
            return ResponseEntity.ok("Detalle de comisiones actualizado");
        }catch (Exception e){
            return ResponseEntity.badRequest().body(e.getMessage());
        }

    }
}
