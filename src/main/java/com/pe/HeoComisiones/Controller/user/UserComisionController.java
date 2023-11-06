package com.pe.HeoComisiones.Controller.user;

import com.pe.HeoComisiones.Entity.Comisiones;
import com.pe.HeoComisiones.Request.ComisionRequest;
import com.pe.HeoComisiones.Services.ComisionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/user/comision")
public class UserComisionController {
    @Autowired
    private ComisionService comisionService;
    
    @GetMapping("/{id}")
    public  ResponseEntity<?> UsergetComisionById(@PathVariable Integer id){
        try {
            return ResponseEntity.ok(comisionService.getComisionesByid(id));
        }catch (Exception e){
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
}
