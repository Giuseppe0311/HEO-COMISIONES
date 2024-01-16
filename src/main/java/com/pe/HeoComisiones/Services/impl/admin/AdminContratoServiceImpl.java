package com.pe.HeoComisiones.Services.impl.admin;

import com.pe.HeoComisiones.DTOs.admin.AdminContratosDTO;
import com.pe.HeoComisiones.Mappers.admin.AdminContratosDTOMapper;
import com.pe.HeoComisiones.Repository.ContratotoDbRepository;
import com.pe.HeoComisiones.Services.admin.AdminContratoService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class AdminContratoServiceImpl implements AdminContratoService {
    public final ContratotoDbRepository contratotoDbRepository;
     public  final  AdminContratosDTOMapper adminContratosDTOMapper;


    @Override
    public List<AdminContratosDTO> getContratos() {
        return contratotoDbRepository.findAll(Sort.by(Sort.Direction.DESC, "idcontrato"))
                .stream()
                .map(adminContratosDTOMapper)
                .toList();
    }
}
