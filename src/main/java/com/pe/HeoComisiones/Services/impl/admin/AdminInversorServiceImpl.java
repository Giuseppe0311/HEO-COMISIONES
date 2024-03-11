package com.pe.HeoComisiones.Services.impl.admin;
import com.pe.HeoComisiones.DTOs.InversorDTO;
import com.pe.HeoComisiones.DTOs.UsuarioDTO;
import com.pe.HeoComisiones.DTOs.admin.AdminConsultas_InversoresDTO;
import com.pe.HeoComisiones.DTOs.admin.Admin_InversoresDTO;
import com.pe.HeoComisiones.Entity.Inversor;
import com.pe.HeoComisiones.Entity.Perfiles;
import com.pe.HeoComisiones.Entity.Usuarios;
import com.pe.HeoComisiones.Exception.InversorNotFoundException;
import com.pe.HeoComisiones.Mappers.InversoresDTOMapper;
import com.pe.HeoComisiones.Mappers.admin.Admin_InversoresDTOMapper;
import com.pe.HeoComisiones.Repository.InversorRepository;
import com.pe.HeoComisiones.Request.InversorRequest;
import com.pe.HeoComisiones.Services.admin.AdminInversorService;
import com.pe.HeoComisiones.Services.admin.AdminUsuarioService;
import com.pe.HeoComisiones.Services.common.CommonClienteService;
import com.pe.HeoComisiones.Services.common.CommonInversorService;
import com.pe.HeoComisiones.Services.common.CommonUsuarioService;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class AdminInversorServiceImpl implements AdminInversorService, CommonInversorService {
    private final Admin_InversoresDTOMapper adminInversoresDTOMapper;
    private final InversorRepository inversorRepository;
    private final CommonClienteService commonClienteService;
    private final CommonUsuarioService commonUsuarioService;
    private final InversoresDTOMapper inversoresDTOMapper;

    public AdminInversorServiceImpl(Admin_InversoresDTOMapper adminInversoresDTOMapper, InversorRepository inversorRepository, @Qualifier("AdminClienteServiceImpl") CommonClienteService commonClienteService, AdminUsuarioService adminUsuarioService, CommonUsuarioService commonUsuarioService, InversoresDTOMapper inversoresDTOMapper) {
        this.adminInversoresDTOMapper = adminInversoresDTOMapper;
        this.inversorRepository = inversorRepository;
        this.commonClienteService = commonClienteService;
        this.commonUsuarioService = commonUsuarioService;
        this.inversoresDTOMapper = inversoresDTOMapper;
    }

    @Override
    public List<Admin_InversoresDTO> getInversores() {
        Sort sort = Sort.by(Sort.Direction.DESC, "id");
        return inversorRepository.findByStatusTrue(sort)
                .stream()
                .map(adminInversoresDTOMapper)
                .collect(Collectors.toList());
    }

    @Override
    public Admin_InversoresDTO getInversorbyId(Integer id) {
        Inversor inversor = verifyInversorExistsById(id);
        return adminInversoresDTOMapper.apply(inversor);
    }

    @Override
    public List<AdminConsultas_InversoresDTO> getInversoresAvances() {
        Sort sort = Sort.by(Sort.Direction.DESC, "id");
        List<Inversor> inversores = inversorRepository.findByStatusTrue(sort);

        // Agrupar por idusuario
        Map<Integer, List<Inversor>> inversoresPorUsuario = inversores.stream()
                .collect(Collectors.groupingBy(inversor -> inversor.getUsuarios().getId()));

        // Crear un DTO para cada grupo
        return inversoresPorUsuario.entrySet().stream()
                .map(entry -> {
                    Integer idUsuario = entry.getKey();
                    Usuarios usuario = entry.getValue().get(0).getUsuarios(); // Asumiendo que todos los inversores en el grupo tienen el mismo usuario
                    UsuarioDTO usuarioDTO = new UsuarioDTO(usuario.getId(), usuario.getName(), usuario.getEmail(), usuario.getDni(), usuario.getProfiles().stream().map(Perfiles::getName).findFirst().orElse(""));
                    List<InversorDTO> inversorDTOs = entry.getValue().stream()
                            .map(inversoresDTOMapper)
                            .collect(Collectors.toList());
                    return new AdminConsultas_InversoresDTO(idUsuario, inversorDTOs, usuarioDTO);
                })
                .collect(Collectors.toList());
    }


    @Override
    @Transactional
    public void updateInversor(Integer id, InversorRequest inversorRequest) {
        Inversor inversor = verifyInversorExistsById(id);
        inversor.setClientes(commonClienteService.verifyClienteExistsById(inversorRequest.getIdcliente()));
        inversor.setContrato(inversorRequest.getContrato());
        inversor.setUsuarios(commonUsuarioService.verifyUsuarioExistsById(inversorRequest.getIdusuario()));
        inversor.setMontoinvertido(inversorRequest.getMontoinvertido());
        inversorRepository.save(inversor);
    }

    @Override
    public void deleteInversor(Integer id) {

    }

    @Override
    public Inversor verifyInversorExistsById(Integer id) {
        return inversorRepository.findById(id)
                .orElseThrow(() -> new InversorNotFoundException("No se encontró el inversor"));
    }
}
