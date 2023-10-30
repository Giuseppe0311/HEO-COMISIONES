package com.pe.HeoComisiones.Services;

import com.pe.HeoComisiones.Entity.Perfiles;
import com.pe.HeoComisiones.Repository.PerfilesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class PerfilService {
    @Autowired
    private PerfilesRepository perfilesRepository;

    public List<Perfiles> getPerfiles() throws Exception{
        return perfilesRepository.findByStatusTrue();
    }
    public List<Perfiles> getPefilesByid(Integer id){
        List<Perfiles> perfiles = new ArrayList<>();
        Perfiles profiles1 = perfilesRepository.findById(id).orElse(null);
        if (profiles1 != null){
            perfiles.add(profiles1);
            return perfiles;
        }
        return perfiles;
    }
    public void savePerfil(Perfiles perfiles){
        perfiles.setStatus(true);
        perfilesRepository.save(perfiles);
    }
    public void updatePerfil(Integer id,Perfiles perfiles)throws Exception{
        Perfiles profiles1 = perfilesRepository.findById(id).orElse(null);
        if (profiles1 != null){
            profiles1.setName(perfiles.getName());
            perfilesRepository.save(profiles1);
        }
        throw new Exception();
    }
    public void deletePerfil(Integer id)throws Exception{
        Perfiles profiles1 = perfilesRepository.findById(id).orElse(null);
        if (profiles1 != null){
            profiles1.setStatus(false);
            perfilesRepository.save(profiles1);
        }
        throw new Exception();
    }
}
