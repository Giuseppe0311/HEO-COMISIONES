package com.pe.HeoComisiones.Controller;

import com.pe.HeoComisiones.Entity.Cliente;
import com.pe.HeoComisiones.Services.ClienteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/cliente")
public class ClienteController {

    @Autowired
    private ClienteService clienteService;

    @GetMapping
    public ResponseEntity<?> GetAllcliente(){
        try {
            return ResponseEntity.ok(clienteService.getcliente());
        }catch (Exception e){
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
    @GetMapping("/{id}")
    public ResponseEntity<?> GetclientebyId(@PathVariable Integer id){
        try {
            return ResponseEntity.ok(clienteService.getclientebyId(id));
        }catch (Exception e){
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PostMapping
    public ResponseEntity<?> Savecliente(@RequestBody Cliente cliente){
        try {
            clienteService.Savecliente(cliente);
            return ResponseEntity.ok().build();
        }catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }


    @PutMapping("/{id}")
    public ResponseEntity<?> Updatecliente(@PathVariable Integer id, @RequestBody Cliente cliente){
        try {
            clienteService.Updatecliente(id, cliente);
            return ResponseEntity.ok().build();
        }catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
}
