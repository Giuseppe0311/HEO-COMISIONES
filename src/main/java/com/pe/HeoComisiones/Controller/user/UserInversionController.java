package com.pe.HeoComisiones.Controller.user;


import com.pe.HeoComisiones.DTOs.InversorDTO;
import com.pe.HeoComisiones.Request.InversorRequest;
import com.pe.HeoComisiones.Request.InversorUsuarioDetalleRequest;
import com.pe.HeoComisiones.Services.common.CommonUsuarioService;
import com.pe.HeoComisiones.Services.user.UsuarioInversorService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/usuario/inversores")
@PreAuthorize("hasAnyRole('USER', 'ADMIN','COMERCIAL','SUPERVISOR','ADMINISTRATIVO')")
public class UserInversionController {
    private final UsuarioInversorService usuarioInversorService;
    private final CommonUsuarioService commonUsuarioService;

    //AQUI PASAMOS COMO PARAMETRO EL ID DE USUARIO OBTENIDO EN EL TOKEN PARA OPTENER LOS DATOS DEL INVERSOR DE DICHO USUARIO
    @GetMapping("/{id}")
    public ResponseEntity<List<InversorDTO>> getInversorbyUsuario(@PathVariable Integer id) {
        return ResponseEntity.ok(usuarioInversorService.getInversoresbyUsuario(id));
    }

    @PostMapping
    public ResponseEntity<?> saveInversor(@RequestBody InversorRequest inversorRequest) {
        usuarioInversorService.saveInversor(inversorRequest);
        return ResponseEntity.ok("Inversor guardado");
    }


}
