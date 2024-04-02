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
    @Scheduled(cron = "0 30 18 28 * ?")
    public void saveDetalleComisiones() {
        // SE BUSCA LOS INVERSORES CON EL STATUS TRUE Y SE VERIFICA SI ESTA VACIA
        List<Inversor> inversoresConStatusTrue = inversorRepository.findByStatusTrue(
                Sort.by(Sort.Direction.ASC, "id")
        );

        if (!inversoresConStatusTrue.isEmpty()) {
            //SE RELIZA UN SET PARA EVITAR LA REPETICION DE USUARIOS DE ACUERDO A LOS INVERSORES ACTIVOS
            Set<Usuarios> uniqueUsuarios = new HashSet<>();
            inversoresConStatusTrue.stream().map(Inversor::getUsuarios).forEach(uniqueUsuarios::add);
            uniqueUsuarios.forEach(this::procesarResultados);
        } else {
            //SI NO SE ECUNETRA SOLO SE IMPRIME
            System.out.println("NO HAY INVERSORES CON STATUS TRUE");
        }


    }

    //ESTE METODO PROCESA LOS RESULTADOS PARA PODER REGISTRAR EN LA BASE DE DATOS
    private void procesarResultados(Usuarios usuarios) {
        double montoinvertido;
        double comisionCorrrespondiente = 0;
        double ganancia = 0;
        //CADA USUARIO TIENE SU PROPIA COMISION ASIGNADA POR LO QUE NO SE PUEDE ESTANDARIZAR
        //POR ESE MOTIVO SE GUARDA EN UN MAP PARA SER ACCEDIDO Y RECORRIDO FACILMENTE
        Map<Double, Double> comisionesPorUsuario = new HashMap<>();
        try {

            //BUSCAMOS LAS COMISIONES SEGUN EL USUARIO Y LO GUARDAMOS EN EL MAP
            //ATRAVEZ DE FUNCION LAMBDA RECORREMOS LA LISTA Y DAMOS CLAVE-VALOR
            comisionRepository.getComisionbyusuario(usuarios.getId()).forEach(
                    comisiones -> comisionesPorUsuario.put(comisiones.getMontomax(), comisiones.getPorcentaje())

            );
            //ATRAVEZ DE PROGRAMACION FUNCIONAL SE BUSCA REDUCIR CODIGO POR LO CUAL
            //SE SUMA CADA MONTOINVERTIDO DE TODOS LOS INVERSIORES QUE PERTENECEN A UN ASESOR
            montoinvertido = inversorRepository.
                    getInversionesByusuario(usuarios.getId()).stream()
                    .mapToDouble(Inversor::getMontoinvertido).sum();
            //SE VALIDA QUE APARTIR DEL MONTO 30K SE APLIQUE LA COMISION CORRESPONDIENTE
            if (montoinvertido < 30000) {
                ganancia = 0;
            } else {
                //RECORREMOS PARA PODER REALIZAR LAS COMPARAACIONES
                for (Map.Entry<Double, Double> entry : comisionesPorUsuario.entrySet()) {
                    //VERIFICA HASTA EL BREAK QUE ES DONDE SE QUEDARA CON EL ULTIMO VALOR QUE REALMENTE DEBERIA TENER
                    if (montoinvertido > entry.getKey()) {
                        comisionCorrrespondiente = entry.getValue();
                        ganancia = montoinvertido * (comisionCorrrespondiente / 100);
                    } else {
                        comisionCorrrespondiente = entry.getValue();
                        ganancia = montoinvertido * (comisionCorrrespondiente / 100);
                        break; // Detener el bucle
                    }
                }
            }
            //SE INICIA EL METODO PRIVADO PARA EMPEZAR CON EL PROCESADO DEL DETALLECOMISION
            procesarDetallecomision(usuarios, montoinvertido, comisionCorrrespondiente, ganancia);

        } catch (Exception ex) {
            System.err.println("Ocurrió un error en procesarResultados: " + ex.getMessage());
        }
    }

    private void procesarDetallecomision(Usuarios usuario, double montoinvertido, double comisionCorrrespondiente, double ganacia) {
        //COMO LOS USUARIOS QUE ESTAN AQUI SIEMPRE VAN A SER CON EL ESTADO EN TRUE NO SE RELIZARON VALIDACIONES DE EMPTY
        //SE OBTIENEN LOS ID'S DE LOS INVERSORES PARA SU POSTERIOR USO
        List<Integer> idsByInversorOfUsuario = inversorRepository.getInversorIdsByUsuario(usuario.getId());
        ResultTrabajadores resultTrabajadores = new ResultTrabajadores();
        resultTrabajadores.setUsuarios(usuario);
        resultTrabajadores.setComisionTotal(comisionCorrrespondiente);
        resultTrabajadores.setMontototal(montoinvertido);
        resultTrabajadores.setGanancia(ganacia);
        resultTrabajadoresRepository.save(resultTrabajadores);
        DetalleComisiones detalleComisiones = new DetalleComisiones();
        detalleComisiones.setResultTrabajadores(resultTrabajadores);
        detalleComisiones.setUsuarios(usuario);
        detalleComisionesRepository.save(detalleComisiones);
        System.out.println("Usuario: " + usuario.getName());
        System.out.println("Usuario CON EL ID : " + usuario.getId());
        System.out.println("Monto Invertido: " + montoinvertido);
        System.out.println("ComisionCorrespondiente: " + comisionCorrrespondiente);
        System.out.println("ganacias" + ganacia);
        System.out.println("idsByInversorOfUsuario: " + idsByInversorOfUsuario);
        //SE ENVIA LOS PARAMETROS NECESARIOS PARA ACTUALIZAR LA BASE DE DATOS DE ACUERDO A LOS INVERSORES ACTIVOS
        inversorRepository.updateInversoresforDetalleComisiones(detalleComisiones, idsByInversorOfUsuario);
    }
}
