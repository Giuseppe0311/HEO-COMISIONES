package com.pe.HeoComisiones.Services;

import com.pe.HeoComisiones.Entity.Comision;
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

@Service
public class ComisionService {
    @Autowired
    private ComisionRepository comisionRepository;
    @Autowired
    private PerfilesRepository perfilesRepository;
    @Autowired
    private SucursalRepository sucursalRepository;
    @Autowired
    private UsuarioRepository usuarioRepository;

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
    public  void SaveComisiones(ComisionRequest comisionRequest)throws Exception{
        Comision comision = new Comision();
        comision.setPorcentaje(comisionRequest.getPorcentaje());
        comision.setGanancia(comisionRequest.getGanancia());
        comision.setPerfiles(perfilesRepository.findById(comisionRequest.getPerfiles()).orElse(null));
        comision.setSucursal(sucursalRepository.findById(comisionRequest.getSucursal()).orElse(null));
        comision.setUsuarios(usuarioRepository.findById(comisionRequest.getUsuarios()).orElse(null));
        comision.setStatus(true);
        comisionRepository.save(comision);
    }
    public void UpdateComisiones(Integer id,ComisionRequest comisionRequest)throws Exception{
        Comision comision1 = comisionRepository.findById(id).orElse(null);
        if (comision1 != null){
            comision1.setPorcentaje(comisionRequest.getPorcentaje());
            comision1.setGanancia(comisionRequest.getGanancia());
            comision1.setPerfiles(perfilesRepository.findById(comisionRequest.getPerfiles()).orElse(null));
            comision1.setSucursal(sucursalRepository.findById(comisionRequest.getSucursal()).orElse(null));
            comision1.setUsuarios(usuarioRepository.findById(comisionRequest.getUsuarios()).orElse(null));
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
