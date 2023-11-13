package com.pe.HeoComisiones.Controller.admin;

import com.pe.HeoComisiones.DTOs.ComisionesDTO;
import com.pe.HeoComisiones.Request.ComisionRequest;
import com.pe.HeoComisiones.Services.ComisionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/admin/comisiones")
public class ComisionController {
    @Autowired
    private ComisionService comisionService;

    @GetMapping
    public ResponseEntity<List<ComisionesDTO>> getComision(){
        try {
            return ResponseEntity.ok(comisionService.getComisiones());
        }catch (Exception e){
            return ResponseEntity.noContent().build();
        }

    }

    @GetMapping("/{id}")
    public  ResponseEntity<?> getComisionById(@PathVariable Integer id){
        try {
            return ResponseEntity.ok(comisionService.getComisionesByid(id));
        }catch (Exception e){
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
    @PostMapping
    public ResponseEntity<?> saveComision(@RequestBody ComisionRequest comisionRequest){
        try {
            comisionService.SaveComisiones(comisionRequest);
            return ResponseEntity.ok("Comision guardada");
        }catch (Exception e){
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
    @PutMapping("/{id}")
    public ResponseEntity<?> updateComision(@PathVariable Integer id,@RequestBody ComisionRequest comisionRequest){
        try {
            comisionService.UpdateComisiones(id,comisionRequest);
            return ResponseEntity.ok("Comision actualizada");
        }catch (Exception e){
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteComision(@PathVariable Integer id){
        try {
            comisionService.DeleteComisiones(id);
            return ResponseEntity.ok("Comision eliminada");
        }catch (Exception e){
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
}
