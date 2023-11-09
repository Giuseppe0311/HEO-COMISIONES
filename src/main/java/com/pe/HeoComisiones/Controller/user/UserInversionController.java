package com.pe.HeoComisiones.Controller.user;


import com.pe.HeoComisiones.Entity.Inversor;
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
    public ResponseEntity<List<Inversor>> getInversorbyUsuario(@PathVariable Integer id){
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
}
