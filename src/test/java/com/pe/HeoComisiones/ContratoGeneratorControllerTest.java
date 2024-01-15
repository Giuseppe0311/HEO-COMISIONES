package com.pe.HeoComisiones;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.pe.HeoComisiones.Controller.admin.ContratoGeneratorController;
import com.pe.HeoComisiones.DTOs.ContratoValuesRequest;
import com.pe.HeoComisiones.Services.admin.AdminContratoGenerateService;
import com.pe.HeoComisiones.Tokens.JwtService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.web.servlet.MockMvc;

import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(ContratoGeneratorController.class)
@AutoConfigureMockMvc(addFilters = false) // Desactiva los filtros de seguridad
public class ContratoGeneratorControllerTest {
    @MockBean
    private JwtService jwtService;
    @Autowired
    private MockMvc mockMvc;
    @MockBean
    private AdminContratoGenerateService commonContratoService;

    @Test
    void whenGenerateContratoWithValidData_thenReturnsOk() throws Exception {
        ContratoValuesRequest contratoValuesRequest = ContratoValuesRequest.builder()
                .idusuario("1") // Asumiendo que el tipo de `idusuario` es int en la clase DTO
                .tipodecontrato("corto")
                .nombre_cliente("Giuseppe Barrera")
                .capital_cliente("10000")
                .vigencia_contrato("50")
                .genero_cliente("MASCULINO") // Asegúrate de que la entidad acepte el valor completo "MASCULINO"
                .vigencia_contrato("cincuenta")
                .tipodocumento_cliente("DNI")
                .fecha_inicio_contrato("02/01/2024") // Asumiendo que hay campos correspondientes en la clase DTO
                .numerodocumento_cliente("71458660")
                .capital_cliente("1200")
                .fecha_fin_contrato("10/01/2024") // Asumiendo que hay campos correspondientes en la clase DTO
                .direccion_cliente("jr jose pardo")
                .utilidad_cliente("11200")
                .correo_cliente("barrer_romero@hotmial.com")
                .departamento_cliente("San Martín")
                .tipo_cuenta_cliente("ahorro")
                .celular_cliente("933632298")
                .provincia_cliente("Picota")
                .cuenta_cliente("12212")
                .fecha_inicio_contrato_letras("02 de Enero del 2024")
                .distrito_cliente("Picota")
                .banco_cliente("Banco de la Nacion")
                .nombre_gerente("ERICK PAREDES PAREDES")
                .ocupacion_cliente("lapiicto")
                .banco_heo("BANBIF")
                .cuenta_heo("03868010700075171155")
                .cargo_gerente("GERENTE GENERAL")
                .dni_gerente("01116630") // Asumiendo que el tipo de `dni_gerente` es String en la clase DTO
                .cronograma(null) // No se proporcionó, por lo que se asume nulo o el valor por defecto
                .build();
        //simulacion de un archivo en bytes
        byte[] dummyBytes = "Contrato en formato byte".getBytes();
        //simulacion de la respuesta del servicio
        Map<String, Object> mockResponse = new HashMap<>();
        mockResponse.put("fileBytes", dummyBytes);
        mockResponse.put("fileName", "contrato.docx");

        when(commonContratoService.generateContrato(any(ContratoValuesRequest.class))).thenReturn(mockResponse);

        MockHttpServletResponse response = mockMvc.perform(post("/contrato-generator/generate")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(new ObjectMapper().writeValueAsString(contratoValuesRequest)))
                .andExpect(status().isOk())
                .andReturn().getResponse();
        assertEquals(200, response.getStatus(), "El código de estado de la respuesta debe ser 200");
        assertEquals("application/vnd.openxmlformats-officedocument.wordprocessingml.document", response.getContentType(), "El tipo de contenido de la respuesta debe ser application/vnd.openxmlformats-officedocument.wordprocessingml.document");
        assertEquals("attachment; filename=\"contrato.docx\"", response.getHeader("Content-Disposition"), "El nombre del archivo adjunto debe ser Giuseppe Barrera_71458660.docx");
        assertNotNull(response.getContentAsByteArray(), "El cuerpo de la respuesta no debería ser nulo");
        assertTrue(response.getContentAsByteArray().length > 0, "El cuerpo de la respuesta no debería estar vacío");
    }
}
