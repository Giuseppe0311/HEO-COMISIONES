package com.pe.HeoComisiones.Validations;

import com.pe.HeoComisiones.Entity.Clientes;

import java.util.List;

public class ValidationUtils {
    public static boolean DniAlreadyExist(List<Clientes> cliente, String dni) {
       for (Clientes clientes : cliente){
           if(clientes.getDni().equals(dni)){
               return true;
           }
       }
         return false;
    }
}
