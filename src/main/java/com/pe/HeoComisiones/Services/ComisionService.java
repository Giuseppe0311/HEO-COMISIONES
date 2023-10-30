package com.pe.HeoComisiones.Services;

import com.pe.HeoComisiones.Entity.Comision;
import com.pe.HeoComisiones.Repository.ComisionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ComisionService {
    @Autowired
    private ComisionRepository comisionRepository;

    public List<Comision> getComisiones(){
        return comisionRepository.findByStatusTrue();
    }
    public List<Comision> getComisionesByid(Integer id){
        List<Comision> comisiones = new ArrayList<>();
        Optional<Comision> comision = comisionRepository.findById(id);
        if (comision.isPresent()){
            comisiones.add(comision.get());
            return comisiones;
        }
        return comisiones;
    }
    public  void SaveComisiones(Comision comision)throws Exception{
        comision.setStatus(true);
        comisionRepository.save(comision);
    }
    public void UpdateComisiones(Integer id,Comision comision)throws Exception{
        Comision comision1 = comisionRepository.findById(id).orElse(null);
        if (comision1 != null){
            comision1.setPorcentaje(comision.getPorcentaje());
            comision1.setGanancia(comision.getGanancia());
            comision1.setPerfiles(comision.getPerfiles());
            comision1.setSucursal(comision.getSucursal());
            comision1.setUsuario(comision.getUsuario());
            comisionRepository.save(comision1);
        }
        throw new Exception();
    }
    public void DeleteComisiones(Integer id)throws Exception{
        Comision comision = comisionRepository.findById(id).orElse(null);
        if (comision != null){
            comision.setStatus(false);
            comisionRepository.save(comision);
        }
        throw new Exception();
    }


}
