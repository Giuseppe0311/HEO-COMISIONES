package com.pe.HeoComisiones.Controller.admin;


import com.pe.HeoComisiones.Entity.Perfiles;
import com.pe.HeoComisiones.Services.PerfilService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/admin/perfiles")
@PreAuthorize("hasRole('ADMIN')")
public class PerfilesController {
    @Autowired
    private PerfilService perfilService;
    @GetMapping
    public ResponseEntity<List<Perfiles>> getPerfiles(){
        try {
            return ResponseEntity.ok(perfilService.getPerfiles());
        }catch (Exception e){
            return ResponseEntity.badRequest().build();
        }
    }
    @GetMapping("/{id}")
    public ResponseEntity<?> getPerfilesById(@PathVariable Integer id){
        try {
            return ResponseEntity.ok(perfilService.getPefilesByid(id));
        }catch (Exception e){
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
    @PostMapping
    public ResponseEntity<?> savePerfil(@RequestBody Perfiles perfiles){
        try {
            perfilService.savePerfil(perfiles);
            return ResponseEntity.ok().build();
        }catch (Exception e){
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
    @PutMapping("/{id}")
    public ResponseEntity<?> updatePerfil(@PathVariable Integer id,@RequestBody Perfiles perfiles){
        try {
            perfilService.updatePerfil(id,perfiles);
            return ResponseEntity.ok().build();
        }catch (Exception e){
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deletePerfil(@PathVariable Integer id){
        try {
            perfilService.deletePerfil(id);
            return ResponseEntity.ok().build();
        }catch (Exception e){
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
}
