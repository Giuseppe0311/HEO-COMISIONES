package com.pe.HeoComisiones.Services;


import com.pe.HeoComisiones.DTOs.InversorDTO;
import com.pe.HeoComisiones.Entity.Inversor;
import com.pe.HeoComisiones.Mappers.InversoresDTOMapper;
import com.pe.HeoComisiones.Repository.ClienteRepository;
import com.pe.HeoComisiones.Repository.InversorRepository;
import com.pe.HeoComisiones.Repository.UsuarioRepository;
import com.pe.HeoComisiones.Request.InversorRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class InversorService {
    private final InversoresDTOMapper inversoresDTOMapper;
    @Autowired
    private InversorRepository inversorRepository;
    @Autowired
    private ClienteRepository clienteRepository;
    @Autowired
    private UsuarioRepository usuarioRepository;

    public InversorService(InversoresDTOMapper inversoresDTOMapper) {
        this.inversoresDTOMapper = inversoresDTOMapper;
    }

    public List<InversorDTO> getInversor(){
        return inversorRepository.findByStatusTrue()
                .stream()
                .map(inversoresDTOMapper)
                .collect(Collectors.toList());
    }
    public List<InversorDTO> getInversorbyId(Integer id)throws Exception{
        Inversor inversor = inversorRepository.findById(id).orElse(null);
        List<InversorDTO> inversores = new ArrayList<>();
        if (inversor != null){
            inversores.add(inversoresDTOMapper.apply(inversor));
            return inversores;
        }
        return inversores;
    }
    public List<InversorDTO> getInversorbyUsuario(Integer id)throws Exception{
        return inversorRepository.getInversionesByusuario(id)
                .stream()
                .map(inversoresDTOMapper)
                .collect(Collectors.toList());
    }
    public void saveInversor(InversorRequest inversorRequest)throws Exception{
        Inversor inversor = new Inversor();
        inversor.setStatus(true);
        inversor.setClientes(clienteRepository.findById(inversorRequest.getIdcliente()).get());
        inversor.setContrato(inversorRequest.getContrato());
        inversor.setUsuarios(usuarioRepository.findById(inversorRequest.getIdusuario()).get());
        inversor.setMontoinvertido(inversorRequest.getMontoinvertido());

        inversorRepository.save(inversor);
    }
    public void updateInversor(Integer id,InversorRequest inversorRequest)throws Exception{
        Inversor inversor = inversorRepository.findById(id).orElse(null);
        if (inversor != null){
            inversor.setClientes(clienteRepository.findById(inversorRequest.getIdcliente()).get());
            inversor.setContrato(inversorRequest.getContrato());
            inversor.setUsuarios(usuarioRepository.findById(inversorRequest.getIdusuario()).get());
            inversor.setMontoinvertido(inversorRequest.getMontoinvertido());
            inversorRepository.save(inversor);
        }
        throw new Exception();
    }
    public void deleteInversor(Integer id)throws Exception{
        Inversor inversor = inversorRepository.findById(id).orElse(null);
        if (inversor != null){
            inversor.setStatus(false);
            inversorRepository.save(inversor);
        }
        throw new Exception();
    }
    public void deleteInversorbyUsuario(Integer id)throws Exception{
        inversorRepository.deleteInversorbyUsuario(id);
    }

}
