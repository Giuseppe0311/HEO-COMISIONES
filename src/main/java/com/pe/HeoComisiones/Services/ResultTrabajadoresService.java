package com.pe.HeoComisiones.Services;


import com.pe.HeoComisiones.DTO.ResulTrabajadoresDTO;
import com.pe.HeoComisiones.Entity.ResultTrabajadores;
import com.pe.HeoComisiones.Mappers.ResulTrabajadoresDTOMapper;
import com.pe.HeoComisiones.Repository.ResultTrabajadoresRepository;
import com.pe.HeoComisiones.Repository.UsuarioRepository;
import com.pe.HeoComisiones.Request.ResultTrabajadoresRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ResultTrabajadoresService {
    private final ResulTrabajadoresDTOMapper resulTrabajadoresDTOMapper;
    @Autowired
    ResultTrabajadoresRepository resultTrabajadoresRepository;
    @Autowired
    UsuarioRepository usuarioRepository;

    public ResultTrabajadoresService(ResulTrabajadoresDTOMapper resulTrabajadoresDTOMapper) {
        this.resulTrabajadoresDTOMapper = resulTrabajadoresDTOMapper;
    }

    public List<ResulTrabajadoresDTO> getresul(){
        return resultTrabajadoresRepository.findAll()
                .stream()
                .map(resulTrabajadoresDTOMapper)
                .collect(Collectors.toList());
    }
    public  List<ResulTrabajadoresDTO> getResultsByid(Integer id)throws Exception{
        List<ResulTrabajadoresDTO> result = new ArrayList<>();
        ResultTrabajadores resulttrabajadores = resultTrabajadoresRepository.findById(id).orElse(null);
        if (resulttrabajadores != null){
            result.add(resulTrabajadoresDTOMapper.apply(resulttrabajadores));
            return result;
        }
        return result;
    }
    public Integer saveResult(ResultTrabajadoresRequest resultTrabajadoresRequest)throws Exception{
            ResultTrabajadores resultTrabajadores = new ResultTrabajadores();
            resultTrabajadores.setComisionTotal(resultTrabajadoresRequest.getComisionTotal());
            resultTrabajadores.setMontototal(resultTrabajadoresRequest.getMontototal());
            resultTrabajadores.setGanancia(resultTrabajadoresRequest.getGanancia());
            resultTrabajadores.setUsuarios(usuarioRepository.findById(resultTrabajadoresRequest.getIdusuario()).orElse(null));
            resultTrabajadoresRepository.save(resultTrabajadores);
            return resultTrabajadores.getId();
    }
    public ResultTrabajadores updateResult(Integer id ,ResultTrabajadoresRequest resultTrabajadoresRequest){
        ResultTrabajadores result = resultTrabajadoresRepository.findById(id).orElse(null);
        if (result != null){
            result.setComisionTotal(resultTrabajadoresRequest.getComisionTotal());
            result.setMontototal(resultTrabajadoresRequest.getMontototal());
            result.setGanancia(resultTrabajadoresRequest.getGanancia());
            return resultTrabajadoresRepository.save(result);
        }
        throw new RuntimeException("No existe el id para actualizar");
    }
}
