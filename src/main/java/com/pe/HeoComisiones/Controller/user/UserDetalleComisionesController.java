package com.pe.HeoComisiones.Controller.user;

import com.pe.HeoComisiones.Request.DetallecoRequest;
import com.pe.HeoComisiones.Services.common.CommonDetalleComisionService;
import com.pe.HeoComisiones.Services.user.UserDetalleComisionesService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/usuario/detallecomisiones")
public class UserDetalleComisionesController {
    private final UserDetalleComisionesService detalleComisionesService;
    private final CommonDetalleComisionService commonDetalleComisionService;

    @GetMapping("/{id}")
    public ResponseEntity<?> userGetDetallebyUsuario(@PathVariable Integer id) {
        return ResponseEntity.ok(commonDetalleComisionService.getdetallebyusuario(id));
    }

    @PostMapping
    public ResponseEntity<?> saveDetalleComisiones(@RequestBody DetallecoRequest detallecoRequest) {
        return ResponseEntity.ok(detalleComisionesService.saveDetalleComisiones(detallecoRequest));
    }
}
