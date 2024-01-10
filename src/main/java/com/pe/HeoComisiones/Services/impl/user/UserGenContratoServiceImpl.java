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

import java.io.*;
import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.*;


// TODO: 2/01/2024 falta obtencion del contador de parte de la base de datos
// TODO : 10/01/2024 No olvidar que las rutas no deben ser relavitas
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
        return Arrays.asList("python3", "documentgenerator.py",
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
        File scriptFile = new File("/documentgenerator.py");

        if (!scriptFile.exists()) {
            throw new FileNotFoundException("El script 'documentgenerator.py' no se encontró en el directorio esperado.");
        }

            // Establecer el archivo como ejecutable
            scriptFile.setExecutable(true);

            // Actualizar el comando para usar el archivo
            command.set(1, scriptFile.getAbsolutePath());

            // Crear un proceso para ejecutar el script
            ProcessBuilder pb = new ProcessBuilder(command);
            pb.redirectErrorStream(true); // Redirige la salida de error a la salida estándar
            Process p = pb.start(); // Ejecuta el proceso

            // Leer la salida del script
            BufferedReader scriptOutputReader = new BufferedReader(new InputStreamReader(p.getInputStream()));
            String line;
            StringBuilder scriptOutput = new StringBuilder();

            while ((line = scriptOutputReader.readLine()) != null) {
                scriptOutput.append(line);
            }
            // Leer la salida de error del script (stderr) y imprimir en la consola
            BufferedReader scriptErrorReader = new BufferedReader(new InputStreamReader(p.getErrorStream()));
            while ((line = scriptErrorReader.readLine()) != null) {
                System.out.println(line); // Imprimir errores en la consola
            }

            // Esperar a que el proceso termine
            int exitCode = p.waitFor();

            // Si hubo error, lanzar una excepción
            if (exitCode != 0) {
                throw new ScriptError("El script terminó con un código de salida: " +exitCode+ " " + scriptOutput);
            }

            // Retornar la salida del script
            return scriptOutput.toString();


    }
    //guardar el contrato en la base de datos
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
