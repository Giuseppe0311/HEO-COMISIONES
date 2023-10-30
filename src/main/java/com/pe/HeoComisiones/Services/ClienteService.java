package com.pe.HeoComisiones.Services;

import com.pe.HeoComisiones.Entity.Cliente;
import com.pe.HeoComisiones.Repository.ClienteRepository;
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

    public void Savecliente(Cliente cliente) throws Exception {
        cliente.setEstado(true);
        clienteRepository.save(cliente);
    }
    public  void Updatecliente(Integer id,Cliente cliente) throws Exception {
        Cliente cliente1 = clienteRepository.findById(id).orElse(null);
        if(cliente1 != null){
            cliente1.setApellido(cliente.getApellido());
            cliente1.setDistrito(cliente.getDistrito());
            cliente1.setNombre(cliente.getNombre());
            cliente1.setTelefono(cliente.getTelefono());
            cliente1.setUsuario(cliente.getUsuario());
            cliente1.setDistrito(cliente.getDistrito());
            cliente1.setProvincia(cliente.getProvincia());
            cliente1.setDepartamento(cliente.getDepartamento());
            clienteRepository.save(cliente1);
        }
        throw new Exception();
    }
    public void Deletecliente(Integer id) throws Exception {
        Cliente cliente = clienteRepository.findById(id).orElse(null);
        if(cliente != null){
            cliente.setEstado(false);
            clienteRepository.save(cliente);
        }
        throw new Exception();
    }


}
