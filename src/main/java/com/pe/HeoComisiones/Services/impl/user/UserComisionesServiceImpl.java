package com.pe.HeoComisiones.Services.impl.user;

import com.pe.HeoComisiones.DTOs.ComisionesDTO;
import com.pe.HeoComisiones.Mappers.ComisionesDTOMapper;
import com.pe.HeoComisiones.Repository.ComisionRepository;
import com.pe.HeoComisiones.Services.user.UserComisionesService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class UserComisionesServiceImpl implements UserComisionesService {
    private final ComisionRepository comisionRepository;
    private final ComisionesDTOMapper comisionesDTOMapper;
    @Override
    public List<ComisionesDTO> getComisionesByUsuario(Integer id) {
        return comisionRepository.getComisionbyusuario(id)
                .stream()
                .map(comisionesDTOMapper)
                .toList();
    }
}
