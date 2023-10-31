package com.pe.HeoComisiones.Services;


import com.pe.HeoComisiones.Entity.Inversor;
import com.pe.HeoComisiones.Repository.ClienteRepository;
import com.pe.HeoComisiones.Repository.InversorRepository;
import com.pe.HeoComisiones.Request.InversorRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class InversorService {
    @Autowired
    private InversorRepository inversorRepository;
    @Autowired
    private ClienteRepository clienteRepository;

    public List<Inversor> getInversor(){
        return inversorRepository.findByStatusTrue();
    }
    public List<Inversor> getInversorbyId(Integer id)throws Exception{
        Inversor inversor = inversorRepository.findById(id).orElse(null);
        List<Inversor> inversores = new ArrayList<>();
        if (inversor != null){
            inversores.add(inversor);
            return inversores;
        }
        return inversores;
    }
    public void saveInversor(InversorRequest inversorRequest)throws Exception{
        Inversor inversor = new Inversor();
        inversor.setStatus(true);
        inversor.setClientes(clienteRepository.findById(inversorRequest.getIdcliente()).get());
        inversor.setContrato(inversorRequest.getContrato());
        inversor.setMontoinvertido(inversorRequest.getMontoinvertido());
        inversorRepository.save(inversor);
    }
    public void updateInversor(Integer id,InversorRequest inversorRequest)throws Exception{
        Inversor inversor = inversorRepository.findById(id).orElse(null);
        if (inversor != null){
            inversor.setClientes(clienteRepository.findById(inversorRequest.getIdcliente()).get());
            inversor.setContrato(inversorRequest.getContrato());
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

}
