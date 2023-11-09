package com.pe.HeoComisiones.Controller.admin;

import com.pe.HeoComisiones.Entity.Sucursales;
import com.pe.HeoComisiones.Services.SucursalService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/admin/sucursal")
public class SucursalController {
    @Autowired
    private SucursalService sucursalService;
    @GetMapping
    public ResponseEntity<List<Sucursales>> getSucursales(){
        try {
            return ResponseEntity.ok(sucursalService.getSucursa());
        }catch (Exception e){
            return ResponseEntity.badRequest().build();
        }
    }
    @GetMapping("/{id}")
    public ResponseEntity<?> getSucursalById(@PathVariable Integer id){
        try {
            return ResponseEntity.ok(sucursalService.getSucursalByid(id));
        }catch (Exception e){
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
    @PostMapping
    public ResponseEntity<?> saveSucursal(@RequestBody Sucursales sucursales){
        try {
            sucursalService.saveSucursal(sucursales);
            return ResponseEntity.ok().build();
        }catch (Exception e){
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
    @PutMapping("/{id}")
    public ResponseEntity<?> updateSucursal(@PathVariable Integer id,@RequestBody Sucursales sucursales){
        try {
            sucursalService.updateSucursal(id, sucursales);
            return ResponseEntity.ok().build();
        }catch (Exception e){
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteSucursal(@PathVariable Integer id){
        try {
            sucursalService.deleteSucursal(id);
            return ResponseEntity.ok().build();
        }catch (Exception e){
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }


}
