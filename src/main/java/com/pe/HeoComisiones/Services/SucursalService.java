package com.pe.HeoComisiones.Services;

import com.pe.HeoComisiones.Entity.Sucursales;
import com.pe.HeoComisiones.Repository.SucursalRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class SucursalService {
    @Autowired
   private SucursalRepository sucursalRepository;
    public List<Sucursales> getSucursa() throws Exception{
       return sucursalRepository.findByStatusTrue();
    }
    public List<Sucursales> getSucursalByid(Integer id) throws Exception{
        List<Sucursales> sucursales = new ArrayList<>();
        Sucursales sucursales1 = sucursalRepository.findById(id).orElse(null);
        if (sucursales1 != null){
            sucursales.add(sucursales1);
            return sucursales;
        }
        return sucursales;
    }
    public void saveSucursal(Sucursales sucursales) throws Exception{
        sucursales.setStatus(true);
        sucursalRepository.save(sucursales);
    }
    public void updateSucursal (Integer id, Sucursales sucursales) throws Exception{
        Sucursales sucursales1 = sucursalRepository.findById(id).orElse(null);
        if (sucursales1 != null){
            sucursales1.setName(sucursales.getName());
            sucursales1.setAddress(sucursales.getAddress());
            sucursales1.setPhone(sucursales.getPhone());
            sucursalRepository.save(sucursales1);
        }
        throw new Exception("No existe el id");
    }
    public void deleteSucursal(Integer id) throws Exception{
        Sucursales sucursales = sucursalRepository.findById(id).orElse(null);
        if (sucursales != null){
            sucursales.setStatus(false);
            sucursalRepository.save(sucursales);
        }
        throw new Exception("No existe el id");
    }

}
