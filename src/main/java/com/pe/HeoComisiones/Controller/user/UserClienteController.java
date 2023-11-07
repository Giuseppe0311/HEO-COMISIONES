package com.pe.HeoComisiones.Controller.user;

import com.pe.HeoComisiones.Request.ClienteRequest;
import com.pe.HeoComisiones.Services.ClienteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user/clientes")
public class UserClienteController {

    @Autowired
    private ClienteService clienteService;
    @GetMapping("/{id}")
    public ResponseEntity<?> UserGetclientebyUsuario(@PathVariable Integer id){
        try {
            return ResponseEntity.ok(clienteService.getclientebyUsuario(id));
        }catch (Exception e){
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
    @GetMapping("/get/{id}")
    public ResponseEntity<?> UserGetclientebyId(@PathVariable Integer id){
        try {
            return ResponseEntity.ok(clienteService.getclientebyId(id));
        }catch (Exception e){
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
    
    @PostMapping
    public ResponseEntity<?> UserSavecliente(@RequestBody ClienteRequest clienteRequest){
        try {
            clienteService.Savecliente(clienteRequest);
            return ResponseEntity.ok().build();
        }catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }


    @PutMapping("/{id}")
    public ResponseEntity<?> UserUpdatecliente(@PathVariable Integer id, @RequestBody ClienteRequest clienteRequest){
        try {
            clienteService.Updatecliente(id, clienteRequest);
            return ResponseEntity.ok().build();
        }catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
}
