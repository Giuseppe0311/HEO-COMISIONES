package com.pe.HeoComisiones.Services;


import com.pe.HeoComisiones.DTOs.InversorDTO;
import com.pe.HeoComisiones.DTOs.admin.Admin_InversoresDTO;
import com.pe.HeoComisiones.Entity.DetalleComisiones;
import com.pe.HeoComisiones.Entity.Inversor;
import com.pe.HeoComisiones.Mappers.InversoresDTOMapper;
import com.pe.HeoComisiones.Mappers.admin.Admin_InversoresDTOMapper;
import com.pe.HeoComisiones.Repository.ClienteRepository;
import com.pe.HeoComisiones.Repository.DetalleComisionesRepository;
import com.pe.HeoComisiones.Repository.InversorRepository;
import com.pe.HeoComisiones.Repository.UsuarioRepository;
import com.pe.HeoComisiones.Request.InversorRequest;
import com.pe.HeoComisiones.Request.InversorUsuarioDetalleRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class InversorService {
    private final InversoresDTOMapper inversoresDTOMapper;
    private final Admin_InversoresDTOMapper adminInversoresDTOMapper;
    @Autowired
    private InversorRepository inversorRepository;
    @Autowired
    private ClienteRepository clienteRepository;
    @Autowired
    private UsuarioRepository usuarioRepository;
    @Autowired
    private DetalleComisionesRepository detalleComisionesRepository;

    public InversorService(InversoresDTOMapper inversoresDTOMapper, Admin_InversoresDTOMapper adminInversoresDTOMapper) {
        this.inversoresDTOMapper = inversoresDTOMapper;
        this.adminInversoresDTOMapper = adminInversoresDTOMapper;
    }

    public List<Admin_InversoresDTO> getInversor() {
        return inversorRepository.findByStatusTrue()
                .stream()
                .map(adminInversoresDTOMapper)
                .collect(Collectors.toList());
    }

    public List<Admin_InversoresDTO> getInversorbyId(Integer id) throws Exception {
        Inversor inversor = inversorRepository.findById(id).orElse(null);
        List<Admin_InversoresDTO> inversores = new ArrayList<>();
        if (inversor != null) {
            inversores.add(adminInversoresDTOMapper.apply(inversor));
            return inversores;
        }
        return inversores;
    }

    public List<InversorDTO> getInversorbyUsuario(Integer id) throws Exception {
        return inversorRepository.getInversionesByusuario(id)
                .stream()
                .map(inversoresDTOMapper)
                .collect(Collectors.toList());
    }

    public void saveInversor(InversorRequest inversorRequest) throws Exception {
        Inversor inversor = new Inversor();
        inversor.setStatus(true);
        inversor.setClientes(clienteRepository.findById(inversorRequest.getIdcliente()).orElse(null));
        inversor.setContrato(inversorRequest.getContrato());
        inversor.setUsuarios(usuarioRepository.findById(inversorRequest.getIdusuario()).orElse(null));
        inversor.setDetalleComisiones(detalleComisionesRepository.findById(inversorRequest.getIddetallecomisiones()).orElse(null));
        inversor.setMontoinvertido(inversorRequest.getMontoinvertido());
        inversorRepository.save(inversor);
    }

    public void updateInversor(Integer id, InversorRequest inversorRequest) throws Exception {
        Inversor inversor = inversorRepository.findById(id).orElse(null);
        if (inversor != null) {
            inversor.setClientes(clienteRepository.findById(inversorRequest.getIdcliente()).orElse(null));
            inversor.setContrato(inversorRequest.getContrato());
            inversor.setUsuarios(usuarioRepository.findById(inversorRequest.getIdusuario()).orElse(null));
            inversor.setMontoinvertido(inversorRequest.getMontoinvertido());
            inversorRepository.save(inversor);
        }
        throw new Exception();
    }

    // ACTUALIZAR EL DETALLE POR PARTE DEL USUARIO
        public void updateInversorbyUsuario(Integer id, InversorUsuarioDetalleRequest inversorUsuarioDetalleRequest) throws Exception {
            List<Inversor> inversores = inversorRepository.getInversionesByusuario(id);
            if (inversores.isEmpty()) {
                throw new Exception("No se encontraron inversores para el usuario.");
            }

            Optional<DetalleComisiones> detalleComisionesOpt = detalleComisionesRepository.findById(inversorUsuarioDetalleRequest.getIddetallecomisiones());
            if (detalleComisionesOpt.isEmpty()) {
                throw new Exception("Detalle de comisiones no encontrado.");
            }

            DetalleComisiones detalleComisiones = detalleComisionesOpt.get();
            for (Inversor inversor : inversores) {
                inversor.setDetalleComisiones(detalleComisiones);
            }

            inversorRepository.saveAll(inversores);
        }

    public void deleteInversor(Integer id) throws Exception {
        Inversor inversor = inversorRepository.findById(id).orElse(null);
        if (inversor != null) {
            inversor.setStatus(false);
            inversorRepository.save(inversor);
        }
        throw new Exception();
    }

    public void deleteInversorbyUsuario(Integer id) throws Exception {
        inversorRepository.deleteInversorbyUsuario(id);
    }

}
