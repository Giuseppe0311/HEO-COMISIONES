package com.pe.HeoComisiones.Services.user;
import com.pe.HeoComisiones.DTOs.ClienteDTO;
import com.pe.HeoComisiones.Request.ClienteRequest;
import java.util.List;

public interface UserClienteService {
    ClienteDTO getclientebyId(Integer id);
    List<ClienteDTO> getclientebyUsuario(Integer id);
    void saveCliente(ClienteRequest clienteRequest);
    void updateCliente(Integer id, ClienteRequest clienteRequest);
    void deleteCliente(Integer id);

}
