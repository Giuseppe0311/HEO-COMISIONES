package com.pe.HeoComisiones.Services;

import com.pe.HeoComisiones.DTOs.ClienteDTO;
import com.pe.HeoComisiones.Entity.Clientes;
import com.pe.HeoComisiones.Mappers.ClienteDTOMapper;
import com.pe.HeoComisiones.Repository.ClienteRepository;
import com.pe.HeoComisiones.Repository.UsuarioRepository;
import com.pe.HeoComisiones.Request.ClienteRequest;
import com.pe.HeoComisiones.Validations.ValidationUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service

public class ClienteService {
    private final ClienteDTOMapper clienteDTOMapper;
    @Autowired
    private ClienteRepository clienteRepository;
    @Autowired
    private UsuarioRepository usuarioRepository;

    public ClienteService(ClienteDTOMapper clienteDTOMapper) {
        this.clienteDTOMapper = clienteDTOMapper;
    }

    public List<ClienteDTO> getcliente(){
        Sort sort  = Sort.by(Sort.Direction.ASC,"id");
        return clienteRepository.findByStatusTrue(sort)
                .stream()
                .map(clienteDTOMapper)
                .collect(Collectors.toList());
    }
    public List<ClienteDTO> getclientebyId(Integer id) throws Exception {
        Optional<Clientes> cliente = clienteRepository.findById(id);
        List<ClienteDTO> clientes1 = new ArrayList<>();
        if(cliente.isPresent()){
            clientes1.add(clienteDTOMapper.apply(cliente.get()));
            return clientes1;
        }
       return clientes1;
    }
    public List<ClienteDTO> getclientebyUsuario(Integer id) throws Exception {
        return clienteRepository.getClientesbyUsuario(id)
                .stream()
                .map(clienteDTOMapper)
                .collect(Collectors.toList());
    }

    public void Savecliente(ClienteRequest clienteRequest) throws Exception {
        List<Clientes> clientesdeusuario = clienteRepository.getClientesbyUsuario(clienteRequest.getUsuarios());
        if(ValidationUtils.DniAlreadyExist(clientesdeusuario,clienteRequest.getDni())){
            throw new Exception("El dni ya existe");
        }
         Clientes clientes = new Clientes();
        clientes.setUsuarios(usuarioRepository.findById(clienteRequest.getUsuarios()).orElse(null));
        clientes.setStatus(true);
        clientes.setApellido(clienteRequest.getApellido());
        clientes.setDistrito(clienteRequest.getDistrito());
        clientes.setNombre(clienteRequest.getNombre());
        clientes.setTelefono(clienteRequest.getTelefono());
        clientes.setDni(clienteRequest.getDni());
        clientes.setDistrito(clienteRequest.getDistrito());
        clientes.setProvincia(clienteRequest.getProvincia());
        clientes.setDepartamento(clienteRequest.getDepartamento());
        clienteRepository.save(clientes);
    }
    public  void Updatecliente(Integer id,ClienteRequest clienteRequest) throws Exception {
        Clientes clientes1 = clienteRepository.findById(id).orElse(null);
        if(clientes1 != null){
            clientes1.setApellido(clienteRequest.getApellido());
            clientes1.setDistrito(clienteRequest.getDistrito());
            clientes1.setNombre(clienteRequest.getNombre());
            clientes1.setTelefono(clienteRequest.getTelefono());
            clientes1.setUsuarios(usuarioRepository.findById(clienteRequest.getUsuarios()).orElse(null));
            clientes1.setDistrito(clienteRequest.getDistrito());
            clientes1.setProvincia(clienteRequest.getProvincia());
            clientes1.setDepartamento(clienteRequest.getDepartamento());
            clienteRepository.save(clientes1);
        }
        throw new Exception();
    }
    public void Deletecliente(Integer id) throws Exception {
        Clientes clientes = clienteRepository.findById(id).orElse(null);
        if(clientes != null){
            clientes.setStatus(false);
            clienteRepository.save(clientes);
        }
        throw new Exception();
    }


}
