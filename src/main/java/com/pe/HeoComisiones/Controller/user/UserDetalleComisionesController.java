package com.pe.HeoComisiones.Controller.user;

import com.pe.HeoComisiones.Services.DetalleComisionesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user/detallecomisiones")
public class UserDetalleComisionesController {
    @Autowired
    private DetalleComisionesService detalleComisionesService;

    @GetMapping("/{id}")
    public ResponseEntity<?> UsergetdetallebyUsuario(@PathVariable Integer id){
        try {
            return ResponseEntity.ok(detalleComisionesService.getdetallebyusuario(id));
        }catch (Exception e){
            return ResponseEntity.badRequest().body(e.getMessage());
        }

    }
}
