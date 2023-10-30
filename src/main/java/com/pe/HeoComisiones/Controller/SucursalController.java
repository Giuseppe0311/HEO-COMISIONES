package com.pe.HeoComisiones.Controller;

import com.pe.HeoComisiones.Entity.Sucursal;
import com.pe.HeoComisiones.Services.SucursalService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/sucursal")
public class SucursalController {
    @Autowired
    private SucursalService sucursalService;
    @GetMapping
    public ResponseEntity<List<Sucursal>> getSucursales(){
        try {
            return ResponseEntity.ok(sucursalService.getSucursa());
        }catch (Exception e){
            return ResponseEntity.badRequest().build();
        }
    }
    @GetMapping("/{id}")
    public ResponseEntity<?> getSucursalById(Integer id){
        try {
            return ResponseEntity.ok(sucursalService.getSucursalByid(id));
        }catch (Exception e){
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
    @PostMapping
    public ResponseEntity<?> saveSucursal(Sucursal sucursal){
        try {
            sucursalService.saveSucursal(sucursal);
            return ResponseEntity.ok().build();
        }catch (Exception e){
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
    @PutMapping("/{id}")
    public ResponseEntity<?> updateSucursal(@PathVariable Integer id, Sucursal sucursal){
        try {
            sucursalService.updateSucursal(id, sucursal);
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
