# Usa una imagen base de Debian o Ubuntu
FROM openjdk:17-slim

# Instala Python y pip
RUN apt-get update && apt-get install -y python3 python3-pip

# Instala las dependencias de Python
RUN pip3 install docxtpl python-docx num2words

# Agrega tu archivo JAR de la aplicación Java
ADD ./HeoComisiones-0.0.1-SNAPSHOT.jar /java-app.jar

# Agrega el script Python y los archivos necesarios al contenedor
ADD ./documentgenerator.py /documentgenerator.py
ADD ./contrato_corto_plazo.docx /contrato_corto_plazo.docx
ADD ./contrato_mediano_plazo.docx /contrato_mediano_plazo.docx
ADD ./ENZO.png /ENZO.png
ADD ./ERICK.png /ERICK.png
ADD ./HUMBERTO.png /HUMBERTO.png
ADD ./ORLANDO.png /ORLANDO.png

# Configura el punto de entrada para ejecutar tu aplicación Java
ENTRYPOINT ["java", "-jar", "/java-app.jar"]
