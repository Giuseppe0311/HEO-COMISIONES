package com.pe.HeoComisiones.Controller.admin;

import com.pe.HeoComisiones.Request.UsuarioRequest;
import com.pe.HeoComisiones.Services.admin.AdminUsuarioService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/admin/usuarios")
@RequiredArgsConstructor
@PreAuthorize("hasRole('ADMIN')")
public class UserController {
    private final AdminUsuarioService usuarioService;

    @GetMapping
    public ResponseEntity<?> getUsuarios() {
        return ResponseEntity.ok(usuarioService.getUsuariosforAdmin());
    }

    @GetMapping("/sincomisiones")
    public ResponseEntity<?> getUsuariossincomisiones() {
        return ResponseEntity.ok(usuarioService.getUsuariosforAdminwithoutcomisiones());
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getUsuariosbyId(@PathVariable Integer id) {

        return ResponseEntity.ok(usuarioService.getUsuariosbyIdforAdmin(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateUsuario(@RequestBody UsuarioRequest usuarioRequest, @PathVariable Integer id) {
        usuarioService.updateUsuario(usuarioRequest, id);
        return ResponseEntity.ok("Usuario Actualizado");
    }

    @DeleteMapping
    public ResponseEntity<?> deleteUsuario(@PathVariable Integer id) {
        usuarioService.deleteUser(id);
        return ResponseEntity.ok("Usuario Eliminado");
    }
}
