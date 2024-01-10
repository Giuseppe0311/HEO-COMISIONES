from docxtpl import DocxTemplate, InlineImage
from docx.shared import Mm  # Para especificar las dimensiones de la imagen
import uuid
import io
import base64
import sys
from num2words import num2words

if len(sys.argv) < 37:
    sys.exit("Error: Faltan argumentos para ejecutar el script correctamente.")

# Recupera los argumentos de la línea de comandos
idusuario = sys.argv[1]
nombre = sys.argv[2]
genero = sys.argv[3]
tipodocumento = sys.argv[4]
dni = sys.argv[5]
direccion = sys.argv[6]
departamento = sys.argv[7]
provincia = sys.argv[8]
distrito = sys.argv[9]
ocupacion = sys.argv[10]
bancoheo = sys.argv[11]
cuentaheo = sys.argv[12]
montocapital = sys.argv[13]
porcentaje = sys.argv[14]
rentasletras = sys.argv[15]
rentomonto = sys.argv[16]
totalmonto = sys.argv[17]
tipocuentacliente = sys.argv[18]
cuentaclient = sys.argv[19]
banco_cliente = sys.argv[20]
vigencia_numero_letras = sys.argv[21]
vigencia_texto_letras = sys.argv[22]
dia_inicio = sys.argv[23]
dia_fin = sys.argv[24]
correo = sys.argv[25]
celular = sys.argv[26]
fecha_inicio_letras = sys.argv[27]
documento_de = sys.argv[28]
generar_documento = sys.argv[29],
mes = sys.argv[30]
anio = sys.argv[31]
dni_gerente = sys.argv[32]
cargo_gerente = sys.argv[33]
cronograma = sys.argv[34]
tipodecontrato = sys.argv[35]
numerodecontrato = sys.argv[36]

# VALIDACIONES TOTALES

# GENERO
apelativo = ''
sustantivo = ''
if genero == 'MASCULINO':
    apelativo = 'Sr.'
    sustantivo = 'El'
else:
    apelativo = 'Sra.'
    sustantivo = 'La'

try:
    # Convertir a letras los montos
    montocapitalenletas = num2words(montocapital, lang='es') + ' con 00/100 soles'
    montototalenletras = num2words(totalmonto, lang='es') + ' con 00/100 soles'

    # convertir la vigencia numeros a numero
    vigencia_numero_letras_a_numero = int(vigencia_numero_letras)
    # DAR FORMATO A LOS NUMEROS O  MONTOS

    # Convertir a float
    montocapital_float = float(montocapital)
    montotal_float = float(totalmonto)

    # Formatear con separadores de miles y dos decimales
    montocapital_formateado = "{:,.2f}".format(montocapital_float)
    montotot_formateado = "{:,.2f}".format(montotal_float)

    plantilla_elegida = ''
    if tipodecontrato == 'corto':
        plantilla_elegida = 'plantilla.docx'
    elif tipodecontrato == 'mediano':
        plantilla_elegida = 'plantilla2.docx'

    # CARGAR PLANTILLA
    if plantilla_elegida:
        template = DocxTemplate(plantilla_elegida)
        if dni_gerente == '01116630':
            imagen = InlineImage(template, 'ERICK.jpg', width=Mm(50))
        elif dni_gerente == '40070789':
            imagen = InlineImage(template, 'ORLANDO.jpg', width=Mm(50))
        elif dni_gerente == '41846665':
            imagen = InlineImage(template, 'HUMBERTO.jpg', width=Mm(50))
        elif dni_gerente == '46416820':
            imagen = InlineImage(template, 'ENZO.jpg', width=Mm(50))
        # Contexto que se pasará a la plantilla
        context = {
            'ID': idusuario,
            'MES': mes,
            'NUMCONTRA': numerodecontrato,
            'ANIO': anio,
            'CARGOGERENTE': cargo_gerente,
            'NOMBREGERENTE': documento_de,
            'DNIGERENTE': dni_gerente,
            'APELATIVO': apelativo,
            'INVERSOR': nombre,
            'TIPODOC': tipocuentacliente,
            'DNI': dni,
            'DIRECCION': direccion,
            'DISTRITO': distrito,
            'PROVINCIA': provincia,
            'DEPARTAMENTO': departamento,
            'SUSTANTIVO': sustantivo,
            'OCUPACION': ocupacion,
            'CAPITAL': montocapital_formateado,
            'MONTOLETRA': montocapitalenletas,
            'CCTE': cuentaheo,
            'BANCO': bancoheo,
            'MONTOTAL': montotot_formateado,
            'MONTOTLET': montototalenletras,
            'TIPOCUENTA': tipocuentacliente,
            'CTACLIENTE': cuentaclient,
            'BANCOCLIENTE': banco_cliente,
            'VIGENCIANUMERO': vigencia_numero_letras,
            'DIAINICIO': dia_inicio,
            'DIAFIN': dia_fin,
            'VIGENCIALETRA': vigencia_texto_letras,
            'CORREO': correo,
            'TELEFONO': celular,
            'FECHALETRA': fecha_inicio_letras,
            'IMG': imagen,
            'CRONOGRAMA': cronograma,
            'PLANMES': vigencia_numero_letras_a_numero - 1,
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
        except Exception as e: (
            sys.exit(f"Error al guardar el archivo: {str(e)}"))
    else:
        print("Tipo de contrato no reconocido. Por favor, especifique 'corto' o 'mediano'.")
except Exception as e:
    sys.exit(f"Error durante la ejecución del script: {str(e)}")
