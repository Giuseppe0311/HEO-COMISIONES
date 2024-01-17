package com.pe.HeoComisiones.Services.impl.admin;

import com.pe.HeoComisiones.DTOs.ClienteDTO;
import com.pe.HeoComisiones.Entity.Clientes;
import com.pe.HeoComisiones.Exception.ClienteNotFoundException;
import com.pe.HeoComisiones.Mappers.ClienteDTOMapper;
import com.pe.HeoComisiones.Repository.ClienteRepository;
import com.pe.HeoComisiones.Services.admin.AdminClientesService;
import com.pe.HeoComisiones.Services.common.CommonClienteService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service("AdminClienteServiceImpl")
@RequiredArgsConstructor
public class AdminClienteServiceImpl implements AdminClientesService, CommonClienteService {
    private final ClienteRepository clienteRepository;
    private final ClienteDTOMapper clienteDTOMapper;

    @Override
    public List<ClienteDTO> getcliente() {
        Sort sort  = Sort.by(Sort.Direction.ASC,"id");
        return clienteRepository.findByStatusTrue(sort)
                .stream()
                .map(clienteDTOMapper)
                .toList();
    }

    @Override
    public Clientes verifyClienteExistsById(Integer id) {
        return clienteRepository.findById(id)
                .orElseThrow(() -> new ClienteNotFoundException("No se encontró el cliente"));
    }
}
