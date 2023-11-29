package com.pe.HeoComisiones.Services.admin;

import com.pe.HeoComisiones.Entity.Sucursales;

import java.util.List;

public interface AdminSucursalesService {
    List<Sucursales> getSucursales();
    Sucursales verifySucursalExistsById(Integer id);
    void saveSucursal(Sucursales sucursales);
    void updateSucursal (Integer id, Sucursales sucursales);
    void deleteSucursal(Integer id);
}
