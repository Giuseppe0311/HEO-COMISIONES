package com.pe.HeoComisiones.Controller.user;


import com.pe.HeoComisiones.Entity.ResultTrabajadores;
import com.pe.HeoComisiones.Services.ResultTrabajadoresService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/usuario/resulttrabajadores")
public class UserResultTrabajadoresController {
    @Autowired
    private ResultTrabajadoresService resultTrabajadoresService;

    @PostMapping
    public ResponseEntity<?> userPostTrabajadores(@RequestBody ResultTrabajadores resultTrabajadores){
        try {
            resultTrabajadoresService.saveResult(resultTrabajadores);
            return ResponseEntity.ok("Trabajadores guardados");
        }catch (Exception e){
            return ResponseEntity.badRequest().body(e.getMessage());
        }

    }
}
