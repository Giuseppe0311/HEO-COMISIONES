package com.pe.HeoComisiones.Validations;

import com.pe.HeoComisiones.DTOs.ContratoValuesRequest;
import com.pe.HeoComisiones.Entity.Clientes;
import com.pe.HeoComisiones.Entity.Usuarios;

import java.util.List;

public class ValidationUtils {
    public static boolean DniAlreadyExist(List<Clientes> cliente, String dni ,Integer idclienteActual) {
       for (Clientes clientes : cliente){
           if(clientes.getDni().equals(dni) && (!clientes.getId().equals(idclienteActual))){
               return true;
           }
       }
         return false;
    }

    public static boolean DniAlreadyExistforUser(List<Usuarios> usuarios, String dni) {
        for (Usuarios usuario : usuarios){
            if(usuario.getDni().equals(dni)){
                return true;
            }
        }
        return false;
    }
    /////////////////////////
//    public static boolean isNullOrEmpty(ContratoValuesRequest contratoValues) {
//        return isNullOrBlank(contratoValues.getIdusuario()) || isNullOrBlank(contratoValues.getNombrecompleto()) || isNullOrBlank(contratoValues.getGenero()) || isNullOrBlank(contratoValues.getTipodocumento()) || isNullOrBlank(contratoValues.getNumero_documento()) || isNullOrBlank(contratoValues.getDireccion()) || isNullOrBlank(contratoValues.getDepartamento()) || isNullOrBlank(contratoValues.getProvincia()) || isNullOrBlank(contratoValues.getDistrito()) || isNullOrBlank(contratoValues.getOcupacion()) || isNullOrBlank(contratoValues.getBancoheo()) || isNullOrBlank(contratoValues.getCuentaheo()) || isNullOrBlank(contratoValues.getCapital()) || isNullOrBlank(contratoValues.getPorcentaje()) || isNullOrBlank(contratoValues.getRentasletras()) || isNullOrBlank(contratoValues.getRentomonto()) || isNullOrBlank(contratoValues.getTotalmonto()) || isNullOrBlank(contratoValues.getTipocuentacliente()) || isNullOrBlank(contratoValues.getCuentacliente()) || isNullOrBlank(contratoValues.getBanco_cliente()) || isNullOrBlank(contratoValues.getVigencia_numero_letras()) || isNullOrBlank(contratoValues.getVigencia_texto_letras()) || isNullOrBlank(contratoValues.getDia_inicio()) || isNullOrBlank(contratoValues.getDia_fin()) || isNullOrBlank(contratoValues.getCorreo()) || isNullOrBlank(contratoValues.getCelular()) || isNullOrBlank(contratoValues.getFecha_inicio_letras())
//                || isNullOrBlank(contratoValues.getDni_gerente())|| isNullOrBlank(contratoValues.getCargo_gerente());
//    }
//    private static boolean isNullOrBlank(String str) {
//        return str == null || str.trim().isEmpty();
//    }
    /////////////////////////
}
