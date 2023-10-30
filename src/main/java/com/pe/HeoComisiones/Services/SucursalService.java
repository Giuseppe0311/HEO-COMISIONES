package com.pe.HeoComisiones.Services;

import com.pe.HeoComisiones.Entity.Sucursal;
import com.pe.HeoComisiones.Repository.SucursalRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class SucursalService {
    @Autowired
   private SucursalRepository sucursalRepository;
    public List<Sucursal> getSucursa() throws Exception{
       return sucursalRepository.findByStatusTrue();
    }
    public List<Sucursal> getSucursalByid(Integer id) throws Exception{
        List<Sucursal> sucursals = new ArrayList<>();
        Sucursal sucursal = sucursalRepository.findById(id).orElse(null);
        if (sucursal != null){
            sucursals.add(sucursal);
            return sucursals;
        }
        return sucursals;
    }
    public void saveSucursal(Sucursal sucursal) throws Exception{
        sucursal.setStatus(true);
        sucursalRepository.save(sucursal);
    }
    public void updateSucursal (Integer id, Sucursal sucursal) throws Exception{
        Sucursal sucursal1 = sucursalRepository.findById(id).orElse(null);
        if (sucursal1 != null){
            sucursal1.setName(sucursal.getName());
            sucursal1.setAddress(sucursal.getAddress());
            sucursal1.setPhone(sucursal.getPhone());
            sucursalRepository.save(sucursal1);
        }
        throw new Exception("No existe el id");
    }
    public void deleteSucursal(Integer id) throws Exception{
        Sucursal sucursal = sucursalRepository.findById(id).orElse(null);
        if (sucursal != null){
            sucursal.setStatus(false);
            sucursalRepository.save(sucursal);
        }
        throw new Exception("No existe el id");
    }

}
