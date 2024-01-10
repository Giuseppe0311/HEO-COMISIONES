FROM openjdk:17-slim

# Instala Python y pip
RUN apt-get update && apt-get install -y python3 python3-pip

# Instala las dependencias de Python
RUN pip3 install docxtpl python-docx num2words

# Agrega tu archivo JAR de la aplicación Java
ADD ./HeoComisiones-0.0.1-SNAPSHOT.jar java-app.jar
# Configura el punto de entrada para ejecutar tu aplicación Java
ENTRYPOINT ["java", "-jar", "/java-app.jar"]