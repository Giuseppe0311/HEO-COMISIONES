package com.pe.HeoComisiones.Services.impl.user;

import com.pe.HeoComisiones.DTOs.ClienteDTO;
import com.pe.HeoComisiones.Entity.Clientes;
import com.pe.HeoComisiones.Exception.DniException;
import com.pe.HeoComisiones.Mappers.ClienteDTOMapper;
import com.pe.HeoComisiones.Repository.ClienteRepository;
import com.pe.HeoComisiones.Request.ClienteRequest;
import com.pe.HeoComisiones.Services.common.CommonClienteService;
import com.pe.HeoComisiones.Services.common.CommonUsuarioService;
import com.pe.HeoComisiones.Services.user.UserClienteService;
import com.pe.HeoComisiones.Validations.ValidationUtils;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class UserClienteServiceImpl implements UserClienteService {
    //TODO: 07/01/2024  FALTA IMPLEMENTAR EL ENDPOINT PARA ELIMINAR UN CLIENTE
    private final CommonClienteService commonClienteService;
    private final ClienteDTOMapper clienteDTOMapper;
    private final ClienteRepository clienteRepository;
    private final CommonUsuarioService commonUsuarioService;


    @Override
    public ClienteDTO   getclientebyId(Integer id) {
        Clientes cliente = commonClienteService.verifyClienteExistsById(id);
        return clienteDTOMapper.apply(cliente);
    }

    @Override
    public List<ClienteDTO> getclientebyUsuario(Integer id) {
        return clienteRepository.getClientesbyUsuario(id)
                .stream()
                .map(clienteDTOMapper)
                .toList();
    }

    @Override
    @Transactional
    public void saveCliente(ClienteRequest clienteRequest) {
        List<Clientes> clientesdeusuario = clienteRepository.getClientesbyUsuario(clienteRequest.getUsuarios());
        if (ValidationUtils.DniAlreadyExist(clientesdeusuario, clienteRequest.getDni(), null)) {
            throw new DniException("El DNI ya existe");
        }
        Clientes clientes = new Clientes();
        clientes.setUsuarios(commonUsuarioService.verifyUsuarioExistsById(clienteRequest.getUsuarios()));
        clientes.setStatus(true);
        clientes.setApellido(clienteRequest.getApellido());
        clientes.setDistrito(clienteRequest.getDistrito());
        clientes.setNombre(clienteRequest.getNombre());
        clientes.setTelefono(clienteRequest.getTelefono());
        clientes.setDni(clienteRequest.getDni());
        clientes.setDistrito(clienteRequest.getDistrito());
        clientes.setProvincia(clienteRequest.getProvincia());
        clientes.setDepartamento(clienteRequest.getDepartamento());
        clientes.setCorreoelectronico(clienteRequest.getCorreoelectronico());
        clienteRepository.save(clientes);

    }

    @Override
    @Transactional
    public void updateCliente(Integer id, ClienteRequest clienteRequest) {
        Clientes clientes = commonClienteService.verifyClienteExistsById(id);

        if (ValidationUtils.DniAlreadyExist(clienteRepository.getClientesbyUsuario(clienteRequest.getUsuarios()), clienteRequest.getDni(), clientes.getId())) {
            throw new DniException("El dni ya existe");
        }
        clientes.setApellido(clienteRequest.getApellido());
        clientes.setDistrito(clienteRequest.getDistrito());
        clientes.setDni(clienteRequest.getDni());
        clientes.setNombre(clienteRequest.getNombre());
        clientes.setTelefono(clienteRequest.getTelefono());
        clientes.setUsuarios(commonUsuarioService.verifyUsuarioExistsById(clienteRequest.getUsuarios()));
        clientes.setDistrito(clienteRequest.getDistrito());
        clientes.setProvincia(clienteRequest.getProvincia());
        clientes.setDepartamento(clienteRequest.getDepartamento());
        clientes.setCorreoelectronico(clienteRequest.getCorreoelectronico());
        clienteRepository.save(clientes);
    }
}
