package com.pe.HeoComisiones.Services;

import com.pe.HeoComisiones.DTOs.ComisionesDTO;
import com.pe.HeoComisiones.Entity.Comisiones;
import com.pe.HeoComisiones.Mappers.ComisionesDTOMapper;
import com.pe.HeoComisiones.Repository.ComisionRepository;
import com.pe.HeoComisiones.Repository.PerfilesRepository;
import com.pe.HeoComisiones.Repository.SucursalRepository;
import com.pe.HeoComisiones.Repository.UsuarioRepository;
import com.pe.HeoComisiones.Request.ComisionRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ComisionService {
    private final ComisionesDTOMapper comisionesDTOMapper;
    @Autowired
    private ComisionRepository comisionRepository;
    @Autowired
    private PerfilesRepository perfilesRepository;
    @Autowired
    private SucursalRepository sucursalRepository;
    @Autowired
    private UsuarioRepository usuarioRepository;

    public ComisionService(ComisionesDTOMapper comisionesDTOMapper) {
        this.comisionesDTOMapper = comisionesDTOMapper;
    }

    public List<ComisionesDTO> getComisiones(){
        return comisionRepository.findByStatusTrue()
                .stream()
                .map(comisionesDTOMapper)
                .collect(Collectors.toList());
    }
    public List<ComisionesDTO> getComisionesByid(Integer id){
        List<ComisionesDTO> comisiones = new ArrayList<>();
        Optional<Comisiones> comision = comisionRepository.findById(id);
        if (comision.isPresent()){
            comisiones.add(comisionesDTOMapper.apply(comision.get()));
            return comisiones;
        }
        return comisiones;
    }
    //LADO DEL USUARIO/////////
    public List<ComisionesDTO> getComisionesByUsuario(Integer id){
        return comisionRepository.getComisionbyusuario(id)
                .stream()
                .map(comisionesDTOMapper)
                .collect(Collectors.toList());
    }
    public  void SaveComisiones(ComisionRequest comisionRequest)throws Exception{
        Comisiones comisiones = new Comisiones();
        comisiones.setPorcentaje(comisionRequest.getPorcentaje());
        comisiones.setMontomax(comisionRequest.getMontomax());
        comisiones.setUsuarios(usuarioRepository.findById(comisionRequest.getUsuarios()).orElse(null));
        comisiones.setStatus(true);
        comisionRepository.save(comisiones);
    }
    public void UpdateComisiones(Integer id,ComisionRequest comisionRequest)throws Exception{
        Comisiones comisiones1 = comisionRepository.findById(id).orElse(null);
        if (comisiones1 != null){
            comisiones1.setPorcentaje(comisionRequest.getPorcentaje());
            comisiones1.setMontomax(comisionRequest.getMontomax());
            comisiones1.setUsuarios(usuarioRepository.findById(comisionRequest.getUsuarios()).orElse(null));
            comisionRepository.save(comisiones1);
        }
        throw new Exception();
    }
    public void DeleteComisiones(Integer id)throws Exception{
        Comisiones comisiones = comisionRepository.findById(id).orElse(null);
        if (comisiones != null){
            comisiones.setStatus(false);
            comisionRepository.save(comisiones);
        }
        throw new Exception();
    }


}
