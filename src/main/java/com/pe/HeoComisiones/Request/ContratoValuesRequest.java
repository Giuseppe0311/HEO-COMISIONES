package com.pe.HeoComisiones.Request;


import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ContratoValuesRequest {
    @NonNull
    private String idusuario;
    @NonNull
    private String generardocumento;
    @NonNull
    private String nombrecompleto;
    @NonNull
    private String capital;
    @NonNull
    private String vigencia_numero_letras;
    @NonNull
    private String genero;
    @NonNull
    private String porcentaje;
    @NonNull
    private String vigencia_texto_letras;
    @NonNull
    private String tipodocumento;
    @NonNull
    private String rentasletras;
    @NonNull
    private String dia_inicio;
    @NonNull
    private String numero_documento;
    @NonNull
    private String rentomonto;
    @NonNull
    private String dia_fin;
    @NonNull
    private String direccion;
    @NonNull
    private String totalmonto;
    @NonNull
    private String correo;
    @NonNull
    private String departamento;
    @NonNull
    private String tipocuentacliente;
    @NonNull
    private String celular;
    @NonNull
    private String provincia;
    @NonNull
    private String cuentacliente;
    @NonNull
    private String fecha_inicio_letras;
    @NonNull
    private String distrito;
    @NonNull
    private String banco_cliente;
    @NonNull
    private String documento_de;
    @NonNull
    private String ocupacion;
    @NonNull
    private String bancoheo;
    @NonNull
    private String cuentaheo;
    @NonNull
    private String dni_gerente;
    @NonNull
    private String cargo_gerente;
    private String cronograma;
    @NonNull
    private String tipodecontrato;
}
