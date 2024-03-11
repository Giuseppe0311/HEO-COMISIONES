package com.pe.HeoComisiones.Controller.user;

import com.pe.HeoComisiones.Request.ClienteRequest;
import com.pe.HeoComisiones.Services.user.UserClienteService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/usuario/clientes")
@PreAuthorize("hasAnyRole('USER', 'ADMIN','COMERCIAL','SUPERVISOR','ADMINISTRATIVO')")

public class UserClienteController {
    //TODO: 07/01/2024  FALTA IMPLEMENTAR EL ENDPOINT PARA ELIMINAR UN CLIENTE
    private final UserClienteService clienteService;

    //AQUI SE OBTIENE EL CLIENTE DE ACUERDO AL USUARIO QUE HA INICIADO SESION (SE PASA COMO PARAMETRO EL ID DEL USUARIO
    // QUE SE OPTIENE DEL TOKEN)
    @GetMapping("/{id}")
    public ResponseEntity<?> userGetclientebyUsuario(@PathVariable Integer id) {
        return ResponseEntity.ok(clienteService.getclientebyUsuario(id));
    }

    //AQUI SE OBTIENE EL ID DEL CLIENTE SELECCIONADO
    @GetMapping("/get/{id}")
    public ResponseEntity<?> userGetclientebyId(@PathVariable Integer id) {
        return ResponseEntity.ok(clienteService.getclientebyId(id));
    }
    @PostMapping
    public ResponseEntity<?> userSavecliente(@RequestBody ClienteRequest clienteRequest) {
        clienteService.saveCliente(clienteRequest);
        return ResponseEntity.ok().body("Cliente guardado");
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> userUpdateCliente(@PathVariable Integer id, @RequestBody ClienteRequest clienteRequest) {
            clienteService.updateCliente(id, clienteRequest);
            return ResponseEntity.ok().body("Cliente actualizado");
    }


}
