package com.pe.HeoComisiones.Controller.admin;

import com.pe.HeoComisiones.Request.UsuarioRequest;
import com.pe.HeoComisiones.Services.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/admin/usuarios")
public class UserController {
    @Autowired
    private UsuarioService usuarioService;

    @GetMapping
    public ResponseEntity<?> getUsuarios() {
        try {
            return ResponseEntity.ok(usuarioService.getUsuariosforAdmin());
        }catch (Exception e){
            return ResponseEntity.badRequest().body(e.getMessage());
        }

    }
    @GetMapping("/sincomisiones")
    public ResponseEntity<?> getUsuariossincomisiones() {
        try {
            return ResponseEntity.ok(usuarioService.getUsuariosforAdminwithoutcomisiones());
        }catch (Exception e){
            return ResponseEntity.badRequest().body(e.getMessage());
        }

    }
    @GetMapping("/{id}")
    public ResponseEntity<?> getUsuariosbyId(@PathVariable Integer id){
        try {
            return ResponseEntity.ok(usuarioService.getUsuariosbyId(id));
        }catch (Exception e){
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
    @PutMapping("/{id}")
    public ResponseEntity<?> updateUsuario(@RequestBody UsuarioRequest usuarioRequest, @PathVariable Integer id){
        try {
            usuarioService.updateUsuario(usuarioRequest,id);
            return ResponseEntity.ok("Usuario Actualizado");
        }catch (Exception e){
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
    @DeleteMapping
    public ResponseEntity<?> deleteUsuario(@PathVariable Integer id){
        try {
            usuarioService.deleteUser(id);
            return ResponseEntity.ok("Usuario Eliminado");
        }catch (Exception e){
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
}
