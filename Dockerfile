# Establecer la imagen base
FROM adoptopenjdk:11-jre-hotspot

# Establecer el directorio de trabajo en el contenedor
WORKDIR /app

# Copiar el archivo JAR de la aplicación al contenedor
COPY target/operacionFuegoQuasar.jar .

# Exponer el puerto en el que la aplicación se ejecuta (si es necesario)
EXPOSE 8080

# Comando para ejecutar la aplicación cuando se inicie el contenedor
CMD ["java", "-jar", "operacionFuegoQuasar.jar"]
