package com.pe.HeoComisiones.Controller.user;

import com.pe.HeoComisiones.Request.ResultTrabajadoresRequest;
import com.pe.HeoComisiones.Services.common.CommonDetalleComisionService;
import com.pe.HeoComisiones.Services.user.UserDetalleComisionesService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/usuario/detallecomisiones")
@PreAuthorize("hasAnyRole('USER', 'ADMIN','COMERCIAL','SUPERVISOR','ADMINISTRATIVO')")
public class UserDetalleComisionesController {
    private final CommonDetalleComisionService commonDetalleComisionService;

    @GetMapping("/{id}")
    public ResponseEntity<?> userGetDetallebyUsuario(@PathVariable Integer id) {
        return ResponseEntity.ok(commonDetalleComisionService.getdetallebyusuario(id));
    }
    //TODO : TENER EN CUENTA EL USO DE SAVE DETALLE COMISIONES PORQUE SERA AUTOMATIZADA

//    @PostMapping("/{id}")
//    public ResponseEntity<?> saveDetalleComisiones(@PathVariable Integer id,  @RequestBody ResultTrabajadoresRequest resultTrabajadoresRequest) {
//        detalleComisionesService.saveDetalleComisiones(id,resultTrabajadoresRequest);
//        return ResponseEntity.ok().body("Su mes comercial se envió correctamente");
//    }
}
