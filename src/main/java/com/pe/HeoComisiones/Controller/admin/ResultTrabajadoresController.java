package com.pe.HeoComisiones.Controller.admin;

import com.pe.HeoComisiones.DTOs.ResulTrabajadoresDTO;
import com.pe.HeoComisiones.Request.ResultTrabajadoresRequest;
import com.pe.HeoComisiones.Services.ResultTrabajadoresService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping ("/admin/resulttrabajadores")

public class ResultTrabajadoresController {

    @Autowired
    ResultTrabajadoresService resultTrabajadoresService;

    @GetMapping
    public ResponseEntity<List<ResulTrabajadoresDTO>> getresult(){
        try {
            return ResponseEntity.ok(resultTrabajadoresService.getresul());
        }catch (Exception e){
            return ResponseEntity.badRequest().build();
        }
    }
    @GetMapping("/{id}")
    public ResponseEntity<?> getresultbyid(@PathVariable Integer id){
        try {
            return ResponseEntity.ok(resultTrabajadoresService.getResultsByid(id));
        }catch (Exception e){
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
    @PostMapping
    public ResponseEntity<?> saveresult(@RequestBody ResultTrabajadoresRequest resultTrabajadoresRequest){
        try {
            return ResponseEntity.ok(resultTrabajadoresService.saveResult(resultTrabajadoresRequest));
        }catch (Exception e){
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
    @PutMapping
    public ResponseEntity<?> updateresult(@PathVariable Integer id,@RequestBody ResultTrabajadoresRequest resultTrabajadoresRequest){
        try {
            return ResponseEntity.ok(resultTrabajadoresService.updateResult(id,resultTrabajadoresRequest));
        }catch (Exception e){
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
}
