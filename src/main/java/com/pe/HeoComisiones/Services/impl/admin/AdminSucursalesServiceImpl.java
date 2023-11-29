package com.pe.HeoComisiones.Services.impl.admin;

import com.pe.HeoComisiones.Entity.Sucursales;
import com.pe.HeoComisiones.Exception.SucursalNotFoundException;
import com.pe.HeoComisiones.Repository.SucursalRepository;
import com.pe.HeoComisiones.Services.admin.AdminSucursalesService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class AdminSucursalesServiceImpl implements AdminSucursalesService {
    private final SucursalRepository sucursalRepository;

    @Override
    public List<Sucursales> getSucursales() {
        return sucursalRepository.findByStatusTrue();
    }

    @Override
    public Sucursales verifySucursalExistsById(Integer id) {
        return sucursalRepository.findById(id).orElseThrow(() ->
                new SucursalNotFoundException("No se encontró la sucursal"));
    }

    @Override
    public void saveSucursal(Sucursales sucursales) {
        sucursales.setStatus(true);
        sucursalRepository.save(sucursales);

    }

    @Override
    public void updateSucursal(Integer id, Sucursales sucursales) {
        Sucursales sucursal = verifySucursalExistsById(id);
        sucursal.setName(sucursales.getName());
        sucursal.setAddress(sucursales.getAddress());
        sucursal.setPhone(sucursales.getPhone());
        sucursalRepository.save(sucursal);
    }

    @Override
    public void deleteSucursal(Integer id) {
        Sucursales sucursales = verifySucursalExistsById(id);
        sucursales.setStatus(false);
        sucursalRepository.save(sucursales);

    }
}
