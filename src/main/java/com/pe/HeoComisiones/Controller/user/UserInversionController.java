package com.pe.HeoComisiones.Controller.user;


import com.pe.HeoComisiones.DTOs.InversorDTO;
import com.pe.HeoComisiones.Request.InversorRequest;
import com.pe.HeoComisiones.Services.InversorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/usuario/inversores")

public class UserInversionController {
    @Autowired
    private InversorService inversionService;
    //AQUI PASAMOS COMO PARAMETRO EL ID DE USUARIO OBTENIDO EN EL TOKEN PARA OPTENER LOS DATOS DEL INVERSOR DE DICHO USUARIO
    @GetMapping("/{id}")
    public ResponseEntity<List<InversorDTO>> getInversorbyUsuario(@PathVariable Integer id){
        try {
            return ResponseEntity.ok(inversionService.getInversorbyUsuario(id));
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
    public ResponseEntity<?> updateInversorbyUsuario(@PathVariable Integer id,@RequestBody InversorRequest inversorRequest){
        try {
            inversionService.updateInversorbyUsuario(id,inversorRequest);
            return ResponseEntity.ok("Inversor actualizado");
        }catch (Exception e){
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteInversorbyUsuario(@PathVariable Integer id){
        try {
            inversionService.deleteInversorbyUsuario(id);
            return ResponseEntity.ok("Inversor eliminado");
        }catch (Exception e){
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
}
