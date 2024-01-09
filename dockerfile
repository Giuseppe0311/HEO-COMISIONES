# Etapa 1: Instala Python y sus dependencias
FROM debian:bullseye AS python-stage

RUN apt-get update && apt-get install -y python3 python3-pip
RUN pip3 install docxtpl python-docx num2words

# Etapa 2: Construye la aplicación Java
FROM openjdk:17-slim

# Copia los resultados de la etapa 1 (Python y dependencias) a esta etapa
COPY --from=python-stage /usr/bin/python3 /usr/bin/python3
COPY --from=python-stage /usr/lib/python3 /usr/lib/python3
COPY --from=python-stage /usr/local/lib/python3.9 /usr/local/lib/python3.9
COPY --from=python-stage /usr/local/bin /usr/local/bin

# Agrega tu archivo JAR de la aplicación Java
ADD ./HeoComisiones-0.0.1-SNAPSHOT.jar java-app.jar

# Configura el punto de entrada para ejecutar tu aplicación Java
ENTRYPOINT ["java", "-jar", "/java-app.jar"]
