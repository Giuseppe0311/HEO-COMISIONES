from docxtpl import DocxTemplate, InlineImage
from docx.shared import Mm  # Para especificar las dimensiones de la imagen
import uuid
import os
import io
import base64
import sys
from num2words import num2words

if len(sys.argv) < 34:
    sys.exit("Error: Faltan argumentos para ejecutar el script correctamente.")

# Recupera los argumentos de la línea de comandos
idusuario = sys.argv[1]
numeroSecuencia = sys.argv[2]
mes = sys.argv[3]
anio = sys.argv[4]
nombre_cliente = sys.argv[5]
numerodocumento_cliente = sys.argv[6]
tipodocumento_cliente = sys.argv[7]
direccion_cliente = sys.argv[8]
distrito_cliente = sys.argv[9]
provincia_cliente = sys.argv[10]
departamento_cliente = sys.argv[11]
cargo_gerente = sys.argv[12]
nombre_gerente = sys.argv[13]
dni_gerente = sys.argv[14]
ocupacion_cliente = sys.argv[15]
capital_cliente = sys.argv[16]
cuenta_heo = sys.argv[17]
banco_heo = sys.argv[18]
utilidad_cliente = sys.argv[19]
cuenta_cliente = sys.argv[20]
banco_cliente = sys.argv[21]
genero_cliente = sys.argv[22]
vigencia_contrato = sys.argv[23]
fecha_inicio_contrato = sys.argv[24]
fecha_fin_contrato = sys.argv[25]
fecha_inicio_contrato_letras = sys.argv[26]
correo_cliente = sys.argv[27]
celular_cliente = sys.argv[28]
cronograma = sys.argv[29]
tipodecontrato = sys.argv[30]
tipo_cuenta_cliente = sys.argv[31]
origen_fondos_cliente = sys.argv[32]
tipo_moneda = sys.argv[33]


def cantidad_en_letras(monto, moneda=tipo_moneda):
    parte_entera = int(monto)
    parte_decimal = int(round((monto - parte_entera) * 100))
    return f"{num2words(parte_entera, lang='es')} y {parte_decimal:02d}/100 {moneda}"


# GENERO
if genero_cliente == 'MASCULINO':
    abreviatura_cliente = 'del Sr'
    apelativo_cliente = 'El Señor'
else:
    abreviatura_cliente = 'de la Sra'
    apelativo_cliente = 'La Señora'
try:
      # Convertir a float
    capital_cliente_float = float(capital_cliente)
    utilidad_cliente_float = float(utilidad_cliente)

    capital_cliente_letras = cantidad_en_letras(capital_cliente_float)
    utilidad_cliente_letras = cantidad_en_letras(utilidad_cliente_float)

    # Formatear con separadores de miles y dos decimales
    if tipo_moneda == 'SOLES':
        capital_cliente_float_formateado = "S/." + "{:,.2f}".format(capital_cliente_float)
        utilidad_cliente_float_formateado = "S/." + "{:,.2f}".format(utilidad_cliente_float)
    else:
        capital_cliente_float_formateado = "$" + "{:,.2f}".format(capital_cliente_float)
        utilidad_cliente_float_formateado = "$" + "{:,.2f}".format(utilidad_cliente_float)

    # TODO: NO OLVIDAR QUE SE DEBE USAR OTRA PLANTIILA PARA EL CONTRATO DE CORTO PLAZO
    plantilla_elegida = ''
    vigencia_contrato_final = 0
    if tipodecontrato == 'corto':
        plantilla_elegida = 'contrato_corto_plazo.docx'
        vigencia_contrato_final = int(vigencia_contrato)
    elif tipodecontrato == 'mediano':
        plantilla_elegida = 'contrato_mediano_plazo.docx'
        vigencia_contrato_final = int(vigencia_contrato)

    # CARGAR PLANTILLA
    if plantilla_elegida:
        template = DocxTemplate(plantilla_elegida)
        if dni_gerente == '01116630':
            imagen = InlineImage(template, 'ERICK.png', width=Mm(50))
        elif dni_gerente == '40070789':
            imagen = InlineImage(template, 'ORLANDO.png', width=Mm(50))
        elif dni_gerente == '41846665':
            imagen = InlineImage(template, 'HUMBERTO.png', width=Mm(50))
        elif dni_gerente == '46416820':
            imagen = InlineImage(template, 'ENZO.png', width=Mm(50))
        # Contexto que se pasará a la plantilla
        context = {
            'ID': '0' + idusuario if int(idusuario) < 10 else idusuario,
            'MES': mes,
            'NUMCONTRA': '0' + numeroSecuencia if int(numeroSecuencia) < 10 else numeroSecuencia,
            'ANIO': anio,
            'APELATIVO': apelativo_cliente,
            'NOMBRECLIENTE': nombre_cliente,
            'TIPODOCUMENTO': tipodocumento_cliente,
            'NUMERODOCUMENTO': numerodocumento_cliente,
            'DIRECCION': direccion_cliente,
            'DISTRITO': distrito_cliente,
            'PROVINCIA': provincia_cliente,
            'DEPARTAMENTO': departamento_cliente,
            'CARGOGERENTE': cargo_gerente,
            'NOMBREGERENTE': nombre_gerente,
            'DNIGERENTE': dni_gerente,
            'OCUPACION': ocupacion_cliente,
            'CAPITAL': capital_cliente_float_formateado,
            'CAPITALLETRA': capital_cliente_letras,
            'CUENTAHEO': cuenta_heo,
            'BANCOHEO': banco_heo,
            'UTILIDAD': utilidad_cliente_float_formateado,
            'UTILIDADLETRAS': utilidad_cliente_letras,
            'CUENTACLIENTE': cuenta_cliente,
            'BANCOCLIENTE': banco_cliente,
            'ABREVIATURA': abreviatura_cliente,
            'VIGENCIA': vigencia_contrato_final,
            'FECHAINICIO': fecha_inicio_contrato,
            'FECHAFIN': fecha_fin_contrato,
            'CORREO': correo_cliente,
            'CELULAR': celular_cliente,
            'FECHAINICIOLETRAS': fecha_inicio_contrato_letras,
            'CRONOGRAMA': cronograma,
            'TIPODECUENTA': tipo_cuenta_cliente,
            'ORIGENFONDOS': origen_fondos_cliente,
            'IMG': imagen,
        }
        template.render(context)
        try:
            file_stream = io.BytesIO()
            template.save(file_stream)
            file_stream.seek(0)
            with open("temp_file.docx", "wb") as temp_file:
                temp_file.write(file_stream.getbuffer())
            file_stream.seek(0)
            encoded_file = base64.b64encode(file_stream.read()).decode('utf-8')
            print(encoded_file)
        except Exception as e:

            sys.exit(f"Error al guardar el archivo: {str(e)}")
        finally:
            file_stream.close()

    else:
        print("Tipo de contrato no reconocido. Por favor, especifique 'corto' o 'mediano'.")
except Exception as e:
    sys.exit(f"Error durante la ejecución del script: {str(e)}")
