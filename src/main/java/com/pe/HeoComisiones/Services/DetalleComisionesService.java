package com.pe.HeoComisiones.Services;

import com.pe.HeoComisiones.DTO.DetalleComisionDTO;
import com.pe.HeoComisiones.Entity.DetalleComisiones;
import com.pe.HeoComisiones.Mappers.DetalleComisionDTOMapper;
import com.pe.HeoComisiones.Repository.DetalleComisionesRepository;
import com.pe.HeoComisiones.Repository.InversorRepository;
import com.pe.HeoComisiones.Repository.ResultTrabajadoresRepository;
import com.pe.HeoComisiones.Repository.UsuarioRepository;
import com.pe.HeoComisiones.Request.DetallecoRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class DetalleComisionesService {
    private  final DetalleComisionDTOMapper detalleComisionDTOMapper;
    @Autowired
    private DetalleComisionesRepository detalleComisionesRepository;
    @Autowired
    private InversorRepository inversorRepository;
    @Autowired
    private ResultTrabajadoresRepository resultTrabajadoresRepository;
    @Autowired
    private UsuarioRepository usuarioRepository;

    public DetalleComisionesService(DetalleComisionDTOMapper detalleComisionDTOMapper) {
        this.detalleComisionDTOMapper = detalleComisionDTOMapper;
    }

    public List<DetalleComisionDTO> getallDetalles(){
        return detalleComisionesRepository.findAll()
                .stream()
                .map(detalleComisionDTOMapper)
                .collect(Collectors.toList());
    }
    public  List<DetalleComisionDTO> getdetallesByid(Integer id) throws Exception{
        List<DetalleComisionDTO> detalleComisiones = new ArrayList<>();
        Optional<DetalleComisiones> detalleComisionesOptional = detalleComisionesRepository.findById(id);
        if (detalleComisionesOptional.isPresent()){
            detalleComisiones.add(detalleComisionDTOMapper.apply(detalleComisionesOptional.get()));
            return detalleComisiones;
        }
        return detalleComisiones;
    }
    public List<DetalleComisionDTO> getdetallebyusuario(Integer idusuario){
        return detalleComisionesRepository.getdetallebyusuario(idusuario)
                .stream()
                .map(detalleComisionDTOMapper)
                .collect(Collectors.toList());
    }

    public void saveDetalle(DetallecoRequest detallecoRequest)throws  Exception{
        DetalleComisiones detalleComisiones = new DetalleComisiones();
        detalleComisiones.setInversor(inversorRepository.findById(detallecoRequest.getInversor()).get());
        detalleComisiones.setResultTrabajadores(resultTrabajadoresRepository.findById(detallecoRequest.getResultTrabajadores()).get());
        detalleComisiones.setMescomercial(detallecoRequest.getMescomercial());
        detalleComisiones.setUsuarios(usuarioRepository.findById(detallecoRequest.getUsuarios()).get());
        detalleComisionesRepository.save(detalleComisiones);
    }
    public void updateDetalle(Integer id,DetallecoRequest detallecoRequest)throws  Exception{
        DetalleComisiones detalleComisiones = detalleComisionesRepository.findById(id).orElse(null);
        if (detalleComisiones != null){
            detalleComisiones.setInversor(inversorRepository.findById(detallecoRequest.getInversor()).get());
            detalleComisiones.setResultTrabajadores(resultTrabajadoresRepository.findById(detallecoRequest.getResultTrabajadores()).get());
            detalleComisiones.setMescomercial(detallecoRequest.getMescomercial());
            detalleComisiones.setUsuarios(usuarioRepository.findById(detallecoRequest.getUsuarios()).get());
            detalleComisionesRepository.save(detalleComisiones);
        }
        throw new Exception("No existe el detalle");
    }
}
