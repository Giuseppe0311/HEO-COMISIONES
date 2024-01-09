package com.pe.HeoComisiones.Services.impl.user;

import com.pe.HeoComisiones.DTOs.ContratoValuesRequest;
import com.pe.HeoComisiones.Entity.Contratos;
import com.pe.HeoComisiones.Entity.Usuarios;
import com.pe.HeoComisiones.Exception.ContratoNotFoundException;
import com.pe.HeoComisiones.Exception.ScriptError;
import com.pe.HeoComisiones.Repository.ContratotoDbRepository;
import com.pe.HeoComisiones.Services.common.CommonContratoService;
import com.pe.HeoComisiones.Services.common.CommonUsuarioService;
import com.pe.HeoComisiones.Utils.Cloudinary.CloudService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.*;


// TODO: 2/01/2024 falta obtencion del contador de parte de la base de datos
@Service
@RequiredArgsConstructor
public class UserGenContratoServiceImpl implements CommonContratoService {
    private final CloudService cloudService;
    private final ContratotoDbRepository contratotoDbRepository;
    private final CommonUsuarioService commonUsuarioService;
    private final  ContratotoDbRepository contratotoDbRepository1;
    @Override
    @Transactional
    // ESTE METODO GENERA EL CONTRATO
    public Map<String,Object> generateContrato(ContratoValuesRequest contratoValuesRequest) throws IOException, InterruptedException {

        LocalDate fechaActual = LocalDate.now();
        // Formateador para extraer solo el mes
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MM");
        DateTimeFormatter formatter2 = DateTimeFormatter.ofPattern("yyyy");
        //Buscamos el contador en la base de datos
        Integer numeroRecibido = obtenerNumeroDeContrato(Integer.valueOf(contratoValuesRequest.getIdusuario()));
        Integer numeroSecuencia = numeroRecibido != null ? numeroRecibido + 1 : 1;

        // Obtener el mes como string
        String mes = fechaActual.format(formatter);
        String anio = fechaActual.format(formatter2);
        List <String> command = buildCommand(contratoValuesRequest, mes, anio,numeroSecuencia);
        //ejecuta el script
        String output = exucuteScript(command);
        // Procesar y subir el archivo generado
        byte[] fileBytes = Base64.getDecoder().decode(output);
        String nombreArchivo =contratoValuesRequest.getNombrecompleto() + "-" + contratoValuesRequest.getCapital() + "-" + mes + "-" + anio + ".docx";
        cloudService.uniqueFileName = nombreArchivo;
        String url = cloudService.upload(fileBytes);
        //guardar el contrato en la base de datos
        saveContratoToDatabase(Integer.valueOf(contratoValuesRequest.getIdusuario()),
                numeroSecuencia,
                nombreArchivo,
                url);
        //DECLARAR EL MAPA
        Map<String,Object> response =new HashMap<>();
        //AGREGAR LOS VALORES AL MAPA
        response.put("fileBytes",fileBytes);
        response.put("fileName",nombreArchivo);
        return  response;
    }

    private List<String> buildCommand(ContratoValuesRequest contratoValuesRequest, String mes, String anio,Integer numeroSecuencia) {
            // SE AÑADEN LOS PARAMETROS PARA EL SCRIPT
        return Arrays.asList("venv/Scripts/python.exe", "documentgenerator.py",
                contratoValuesRequest.getIdusuario(),
                contratoValuesRequest.getNombrecompleto(),
                contratoValuesRequest.getGenero(),
                contratoValuesRequest.getTipodocumento(),
                contratoValuesRequest.getNumero_documento(),
                contratoValuesRequest.getDireccion()
                , contratoValuesRequest.getDepartamento(),
                contratoValuesRequest.getProvincia(),
                contratoValuesRequest.getDistrito(),
                contratoValuesRequest.getOcupacion(),
                contratoValuesRequest.getBancoheo(),
                contratoValuesRequest.getCuentaheo(),
                contratoValuesRequest.getCapital(),
                contratoValuesRequest.getPorcentaje(),
                contratoValuesRequest.getRentasletras(),
                contratoValuesRequest.getRentomonto(),
                contratoValuesRequest.getTotalmonto(),
                contratoValuesRequest.getTipocuentacliente(),
                contratoValuesRequest.getCuentacliente(),
                contratoValuesRequest.getBanco_cliente(),
                contratoValuesRequest.getVigencia_numero_letras(),
                contratoValuesRequest.getVigencia_texto_letras(),
                contratoValuesRequest.getDia_inicio(),
                contratoValuesRequest.getDia_fin(),
                contratoValuesRequest.getCorreo(),
                contratoValuesRequest.getCelular(),
                contratoValuesRequest.getFecha_inicio_letras(),
                contratoValuesRequest.getDocumento_de(),
                contratoValuesRequest.getGenerardocumento(),
                mes,
                anio,
                contratoValuesRequest.getDni_gerente(),
                contratoValuesRequest.getCargo_gerente(),
                contratoValuesRequest.getCronograma() != null && !contratoValuesRequest.getCronograma().isEmpty()
                        ? contratoValuesRequest.getCronograma()
                        : "",
                contratoValuesRequest.getTipodecontrato(),
                String.valueOf(numeroSecuencia)
        );
    }
    private String exucuteScript(List<String> command) throws IOException, InterruptedException {
        ProcessBuilder pb = new ProcessBuilder(command);
        pb.directory(new File("src/main/resources/scripts")); // Establece el directorio de trabajo
        pb.redirectErrorStream(true);// Redirige la salida de error a la salida estandar
        Process p = pb.start(); // Ejecuta el proceso
        BufferedReader scriptOutputReader  = new BufferedReader(new InputStreamReader(p.getInputStream()));// Lee la salida del script
        BufferedReader scriptErrorReader = new BufferedReader(new InputStreamReader(p.getErrorStream()));// Lee la salida de error del script
        String line;
        StringBuilder errorLog  = new StringBuilder();// Almacena la salida de error
        StringBuilder scriptOutput  = new StringBuilder();// Almacena la salida del script
        // Lee la salida del script (donde se supone que está la cadena base64 del archivo)
        while ((line = scriptOutputReader.readLine()) != null) {
            scriptOutput.append(line);
        }
        // Lee la salida de error del script
        while ((line = scriptErrorReader.readLine()) != null) {
            errorLog.append(line);
        }
        // Espera a que el proceso termine
        int exitCode = p.waitFor();
        // Si hubo error, lanza una excepción
        if (!errorLog.isEmpty()) {
            throw new ScriptError("Error en el script: " + errorLog.toString());
        }
        // Si el script terminó con un código de salida distinto de 0, lanza una excepción
        if (exitCode != 0) {
            throw new ScriptError("El script terminó con un código de salida: " + exitCode);
        }
        // Retorna la salida del script
        return scriptOutput.toString();
    }
    //guardar el contrato en la base de datos
    // contratoValuesRequest.getNombrecompleto() + "-" + contratoValuesRequest.getCapital() + "-" + mes + "-" + anio + ".docx";
    private void saveContratoToDatabase(Integer idusuario,
                                        Integer numeroSecueciaContrato,
                                        String codigoContrato,
                                        String urlContrato){
        Usuarios user = commonUsuarioService.verifyUsuarioExistsById(idusuario);
        Contratos contratos = new Contratos();
        contratos.setUsuarios(user);
        contratos.setNumeroSecueciaContrato(numeroSecueciaContrato);
        contratos.setEstado("activo");
        contratos.setFechaCreacion(new Timestamp(System.currentTimeMillis()));
        contratos.setCodigoContrato(codigoContrato);
        contratos.setUrlContrato(urlContrato);
        contratotoDbRepository.save(contratos);
    }
    private Integer obtenerNumeroDeContrato(Integer idusuario){
        Contratos contratos =  contratotoDbRepository1.findLastContrato(idusuario);
        if (contratos != null){
            return contratos.getNumeroSecueciaContrato();
        }
        return null;

    }

}
