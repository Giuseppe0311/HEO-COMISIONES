package com.pe.HeoComisiones.Services;

import com.pe.HeoComisiones.Entity.DetalleComisiones;
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

@Service
public class DetalleComisionesService {
    @Autowired
    private DetalleComisionesRepository detalleComisionesRepository;
    @Autowired
    private InversorRepository inversorRepository;
    @Autowired
    private ResultTrabajadoresRepository resultTrabajadoresRepository;
    @Autowired
    private UsuarioRepository usuarioRepository;

    public List<DetalleComisiones> getallDetalles(){
        return detalleComisionesRepository.findAll();
    }
    public  List<DetalleComisiones> getdetallesByid(Integer id) throws Exception{
        List<DetalleComisiones> detalleComisiones = new ArrayList<>();
        Optional<DetalleComisiones> detalleComisionesOptional = detalleComisionesRepository.findById(id);
        if (detalleComisionesOptional.isPresent()){
            detalleComisiones.add(detalleComisionesOptional.get());
            return detalleComisiones;
        }
        return detalleComisiones;
    }
    public List<DetalleComisiones> getdetallebyusuario(Integer idusuario){
        return detalleComisionesRepository.getdetallebyusuario(idusuario);
    }

    public void saveDetalle(DetallecoRequest detallecoRequest)throws  Exception{
        DetalleComisiones detalleComisiones = new DetalleComisiones();
        detalleComisiones.setInversor(inversorRepository.findById(detallecoRequest.getInversor()).get());
        detalleComisiones.setResultTrabajadores(resultTrabajadoresRepository.findById(detallecoRequest.getResultTrabajadores()).get());
        detalleComisiones.setMescomercial(detallecoRequest.getMescomercial());
        detalleComisionesRepository.save(detalleComisiones);
    }
    public void updateDetalle(Integer id,DetallecoRequest detallecoRequest)throws  Exception{
        Optional<DetalleComisiones> detalleComisionesOptional = detalleComisionesRepository.findById(id);
        if (detalleComisionesOptional.isPresent()) {
            DetalleComisiones detalleComisiones = new DetalleComisiones();
            detalleComisiones.setInversor(inversorRepository.findById(detallecoRequest.getInversor()).get());
            detalleComisiones.setResultTrabajadores(resultTrabajadoresRepository.findById(detallecoRequest.getResultTrabajadores()).get());
            detalleComisiones.setMescomercial(detallecoRequest.getMescomercial());
            detalleComisionesRepository.save(detalleComisiones);
        }
    }
}
