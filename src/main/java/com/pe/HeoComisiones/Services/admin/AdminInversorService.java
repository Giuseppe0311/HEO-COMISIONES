package com.pe.HeoComisiones.Services.admin;

import com.pe.HeoComisiones.DTOs.InversorDTO;
import com.pe.HeoComisiones.DTOs.admin.AdminConsultas_InversoresDTO;
import com.pe.HeoComisiones.DTOs.admin.Admin_InversoresDTO;
import com.pe.HeoComisiones.Entity.Inversor;
import com.pe.HeoComisiones.Request.InversorRequest;

import java.util.List;

public interface AdminInversorService {
    List<Admin_InversoresDTO> getInversores();
    Admin_InversoresDTO getInversorbyId(Integer id);
    List<AdminConsultas_InversoresDTO> getInversoresAvances();
    void updateInversor(Integer id, InversorRequest inversorRequest);
    void deleteInversor(Integer id);
}
