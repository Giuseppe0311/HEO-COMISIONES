package com.pe.HeoComisiones.DTOs;


import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
//!ES PROBABLE QUE QUIERAS CAMBIAR EL TIPO DE DATO DE ALGUNOS ATRIBUTOS PERO TEN EN CUENTA QUE ESTAMOS PASANDO
//ESTOS ATRIBUTOS A UN SCRIPT DE PYTHON EL CUAL ESPERA UN STRING Y NO UN INTEGER O UN DOUBLE
public class ContratoValuesRequest {
    private String idusuario;
    private String mes_cabecera;
    private  String anio_cabecera;
    private String nombre_cliente;
    private String numerodocumento_cliente;
    private String tipodocumento_cliente;
    private String direccion_cliente;
    private String distrito_cliente;
    private String provincia_cliente;
    private String departamento_cliente;
    private String cargo_gerente;
    private String nombre_gerente;
    private String dni_gerente;
    private String ocupacion_cliente;
    // EL CAPITAL_CLIENTE SERA CONVERTIDO EN LETRAS EN EL SCRIPT DE PYTHON
    private String capital_cliente;
    private String cuenta_heo;
    private String banco_heo;
    //lA UTILIDAD SERA CONVERTIDO EN LETRAS EN EL SCRIPT DE PYTHON
    private String utilidad_cliente;
    private String cuenta_cliente;
    private String banco_cliente;
    //para elegirar la abreviatura del cliente
    private String genero_cliente;
    private String vigencia_contrato;
    private String fecha_inicio_contrato;
    private String fecha_fin_contrato;
    private String fecha_inicio_contrato_letras;
    private String correo_cliente;
    private String celular_cliente;
    private String cronograma;
    private String tipodecontrato;
    private String tipo_cuenta_cliente;
    private String origen_fondos_cliente;
    //tipo de moneda DOLARES O SOLES
    private String tipo_moneda;
}
