package com.pe.HeoComisiones.Validations;

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
}
