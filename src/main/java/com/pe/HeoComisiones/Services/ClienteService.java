package com.pe.HeoComisiones.Services;

import com.pe.HeoComisiones.Entity.Cliente;
import com.pe.HeoComisiones.Repository.ClienteRepository;
import com.pe.HeoComisiones.Repository.UsuarioRepository;
import com.pe.HeoComisiones.Request.ClienteRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service

public class ClienteService {
    @Autowired
    private ClienteRepository clienteRepository;
    @Autowired
    private UsuarioRepository usuarioRepository;

    public List<Cliente> getcliente(){
        Sort sort  = Sort.by(Sort.Direction.ASC,"id");
        return clienteRepository.findByStatusTrue(sort);
    }
    public List<Cliente> getclientebyId(Integer id) throws Exception {
        Optional<Cliente> cliente = clienteRepository.findById(id);
        List<Cliente> cliente1 = new ArrayList<>();
        if(cliente.isPresent()){
            cliente1.add(cliente.get());
            return cliente1;
        }
       return cliente1;
    }

    public void Savecliente(ClienteRequest clienteRequest) throws Exception {
        Cliente cliente = new Cliente();
        cliente.setApellido(clienteRequest.getApellido());
        cliente.setDistrito(clienteRequest.getDistrito());
        cliente.setNombre(clienteRequest.getNombre());
        cliente.setTelefono(clienteRequest.getTelefono());
        cliente.setUsuarios(usuarioRepository.findById(clienteRequest.getUsuarios()).orElse(null));
        cliente.setDistrito(clienteRequest.getDistrito());
        cliente.setProvincia(clienteRequest.getProvincia());
        cliente.setDepartamento(clienteRequest.getDepartamento());
        clienteRepository.save(cliente);
    }
    public  void Updatecliente(Integer id,ClienteRequest clienteRequest) throws Exception {
        Cliente cliente1 = clienteRepository.findById(id).orElse(null);
        if(cliente1 != null){
            cliente1.setApellido(clienteRequest.getApellido());
            cliente1.setDistrito(clienteRequest.getDistrito());
            cliente1.setNombre(clienteRequest.getNombre());
            cliente1.setTelefono(clienteRequest.getTelefono());
            cliente1.setUsuarios(usuarioRepository.findById(clienteRequest.getUsuarios()).orElse(null));
            cliente1.setDistrito(clienteRequest.getDistrito());
            cliente1.setProvincia(clienteRequest.getProvincia());
            cliente1.setDepartamento(clienteRequest.getDepartamento());
            clienteRepository.save(cliente1);
        }
        throw new Exception();
    }
    public void Deletecliente(Integer id) throws Exception {
        Cliente cliente = clienteRepository.findById(id).orElse(null);
        if(cliente != null){
            cliente.setStatus(false);
            clienteRepository.save(cliente);
        }
        throw new Exception();
    }


}
