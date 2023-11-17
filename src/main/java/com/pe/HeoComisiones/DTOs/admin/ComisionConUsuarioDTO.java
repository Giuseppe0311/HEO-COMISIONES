package com.pe.HeoComisiones.DTOs.admin;

import com.pe.HeoComisiones.DTOs.ComisionesDTO;
import com.pe.HeoComisiones.DTOs.UsuarioDTO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

public record ComisionConUsuarioDTO(
         Integer id,
         UsuarioDTO usuario,
        List<ComisionesDTO> comisiones
) {

}
