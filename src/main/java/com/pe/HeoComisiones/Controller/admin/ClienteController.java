package com.pe.HeoComisiones.Controller.admin;

import com.pe.HeoComisiones.Services.admin.AdminClientesService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/admin/clientes")
@PreAuthorize("hasRole('ADMIN')")
public class ClienteController {
    private final AdminClientesService adminClientesService;
    @GetMapping
    public ResponseEntity<?> getClientes(){
        return ResponseEntity.ok().body(adminClientesService.getcliente());
    }

}
