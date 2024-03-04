package com.pe.HeoComisiones.Controller.admin;

import com.pe.HeoComisiones.Entity.Sucursales;
import com.pe.HeoComisiones.Services.admin.AdminSucursalesService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/admin/sucursal")
@PreAuthorize("hasRole('ADMIN')")
public class SucursalController {
   private  final AdminSucursalesService sucursalService;
    @GetMapping
    public ResponseEntity<List<Sucursales>> getSucursales(){
            return ResponseEntity.ok(sucursalService.getSucursales());
    }
    @GetMapping("/{id}")
    public ResponseEntity<?> getSucursalById(@PathVariable Integer id){
            return ResponseEntity.ok(sucursalService.verifySucursalExistsById(id));
    }
    @PostMapping
    public ResponseEntity<?> saveSucursal(@RequestBody Sucursales sucursales){
            sucursalService.saveSucursal(sucursales);
            return ResponseEntity.ok().body("Sucursal guardada");
    }
    @PutMapping("/{id}")
    public ResponseEntity<?> updateSucursal(@PathVariable Integer id,@RequestBody Sucursales sucursales){
            sucursalService.updateSucursal(id, sucursales);
            return ResponseEntity.ok().body("Sucursal actualizada");
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteSucursal(@PathVariable Integer id){
            sucursalService.deleteSucursal(id);
            return ResponseEntity.ok().body("Sucursal eliminada");
    }


}
