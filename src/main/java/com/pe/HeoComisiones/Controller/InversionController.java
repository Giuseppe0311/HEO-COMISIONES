package com.pe.HeoComisiones.Controller;

import com.pe.HeoComisiones.Entity.Inversor;
import com.pe.HeoComisiones.Request.InversorRequest;
import com.pe.HeoComisiones.Services.InversorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/inversion")
public class InversionController {
    @Autowired
    private InversorService inversionService;
    @GetMapping
    public ResponseEntity<List<Inversor>> getInversor(){
        try {
            return ResponseEntity.ok(inversionService.getInversor());
        }catch (Exception e){
            return ResponseEntity.badRequest().build();
        }
    }
    @GetMapping("/{id}")
    public ResponseEntity<List<Inversor>> getInversorbyId(@PathVariable Integer id){
        try {
            return ResponseEntity.ok(inversionService.getInversorbyId(id));
        }catch (Exception e){
            return ResponseEntity.badRequest().build();
        }
    }
    @PostMapping
    public ResponseEntity<?> saveInversor(@RequestBody InversorRequest inversorRequest){
        try {
            inversionService.saveInversor(inversorRequest);
            return ResponseEntity.ok("Inversor guardado");
        }catch (Exception e){
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
    @PutMapping("/{id}")
    public ResponseEntity<?> updateInversor(@PathVariable Integer id,@RequestBody InversorRequest inversorRequest){
        try {
            inversionService.updateInversor(id,inversorRequest);
            return ResponseEntity.ok("Inversor actualizado");
        }catch (Exception e){
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteInversor(@PathVariable Integer id){
        try {
            inversionService.deleteInversor(id);
            return ResponseEntity.ok("Inversor eliminado");
        }catch (Exception e){
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
}
