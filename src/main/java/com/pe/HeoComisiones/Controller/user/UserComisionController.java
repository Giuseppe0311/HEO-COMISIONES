package com.pe.HeoComisiones.Controller.user;

import com.pe.HeoComisiones.Services.user.UserComisionesService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;


@RestController
@RequiredArgsConstructor
@PreAuthorize("hasAnyRole('USER', 'ADMIN','COMERCIAL','SUPERVISOR','ADMINISTRATIVO')")
@RequestMapping("/usuario/comisiones")
public class UserComisionController {
    private final UserComisionesService comisionService;

    @GetMapping("/{id}")
    public ResponseEntity<?> usergetComisionesByUsuario(@PathVariable Integer id) {
        return ResponseEntity.ok(comisionService.getComisionesByUsuario(id));
    }
}
