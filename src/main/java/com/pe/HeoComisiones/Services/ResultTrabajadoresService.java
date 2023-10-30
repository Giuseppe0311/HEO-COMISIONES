package com.pe.HeoComisiones.Services;


import com.pe.HeoComisiones.Entity.ResultTrabajadores;
import com.pe.HeoComisiones.Repository.ResultTrabajadoresRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ResultTrabajadoresService {
    @Autowired
    ResultTrabajadoresRepository resultTrabajadoresRepository;

    public List<ResultTrabajadores> getresul(){
        return resultTrabajadoresRepository.findAll();
    }
    public  List<ResultTrabajadores> getResultsByid(Integer id)throws Exception{
        List<ResultTrabajadores> result = new ArrayList<>();
        ResultTrabajadores resulttrabajadores = resultTrabajadoresRepository.findById(id).orElse(null);
        if (resulttrabajadores != null){
            result.add(resulttrabajadores);
            return result;
        }
        return result;
    }
    public ResultTrabajadores saveResult(ResultTrabajadores resultTrabajadores){
        return resultTrabajadoresRepository.save(resultTrabajadores);
    }
    public ResultTrabajadores updateResult(Integer id ,ResultTrabajadores resultTrabajadores){
        ResultTrabajadores result = resultTrabajadoresRepository.findById(id).orElse(null);
        if (result != null){
            result.setComisionTotal(resultTrabajadores.getComisionTotal());
            result.setMontototal(resultTrabajadores.getMontototal());
            result.setGanancia(resultTrabajadores.getGanancia());
            return resultTrabajadoresRepository.save(result);
        }
        throw new RuntimeException("No existe el id para actualizar");
    }
}
