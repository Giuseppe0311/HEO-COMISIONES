package com.pe.HeoComisiones.Scheduled;

import com.pe.HeoComisiones.Entity.*;
import com.pe.HeoComisiones.Repository.*;
import com.pe.HeoComisiones.Services.user.UserDetalleComisionesService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Sort;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.*;

@Component
@RequiredArgsConstructor
@Transactional
public class UserDetalleComisionesServiceImplCronJob implements UserDetalleComisionesService {
    private final InversorRepository inversorRepository;
    private final ResultTrabajadoresRepository resultTrabajadoresRepository;
    private final DetalleComisionesRepository detalleComisionesRepository;
    private final ComisionRepository comisionRepository;


    //ENVIAR LOS 28 DE CADA MES A LAS 11:30 PM
    @Override
    @Scheduled(cron = "* * * * * *")
    public void saveDetalleComisiones() {
        System.out.println(new Date());
    }
}
