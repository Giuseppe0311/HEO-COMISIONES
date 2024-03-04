package com.pe.HeoComisiones.Controller.admin;


import com.pe.HeoComisiones.DTOs.admin.AdminContratosDTO;
import com.pe.HeoComisiones.Entity.Contratos;
import com.pe.HeoComisiones.Repository.ContratotoDbRepository;
import com.pe.HeoComisiones.Services.admin.AdminContratoService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/admin/contratos")
@PreAuthorize("hasRole('ADMIN')")
public class ContratoController {
    private final AdminContratoService contratoService;
    private  final ContratotoDbRepository contratotoDbRepository;
    @GetMapping
    public List<AdminContratosDTO> getContratos(){
        return contratoService.getContratos();
    }
}
