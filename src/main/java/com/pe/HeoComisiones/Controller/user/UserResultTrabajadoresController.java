package com.pe.HeoComisiones.Controller.user;


import com.pe.HeoComisiones.Request.ResultTrabajadoresRequest;
import com.pe.HeoComisiones.Services.user.UserResultTrabajadoresService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/usuario/resulttrabajadores")
public class UserResultTrabajadoresController {
   private final UserResultTrabajadoresService resultTrabajadoresService;

    @PostMapping
    public ResponseEntity<?> userPostTrabajadores(@RequestBody ResultTrabajadoresRequest resultTrabajadoresRequest){
            return ResponseEntity.ok(resultTrabajadoresService.saveResult(resultTrabajadoresRequest));
    }
}
