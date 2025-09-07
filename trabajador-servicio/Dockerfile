# ETAPA CONSTRUCCION
# contexto construccion desde la raiz
FROM maven:3.9-eclipse-temurin-21 AS builder

# simula la raiz
WORKDIR /mainApp
# guardamos dependencias padre
COPY ./pom.xml ./pom.xml

# simula carpeta microservicio
WORKDIR /mainApp/app
# guardamos depencias y code
COPY trabajador-servicio/pom.xml .

RUN mvn dependency:go-offline -B # instalamos dependencias necesarias

COPY trabajador-servicio/src/ ./src/

RUN mvn clean package -DskipTests # compilamos excluyendo tests

# ETAPA EJECUCION
FROM eclipse-temurin:21-jre-alpine

RUN apk add --no-cache curl # herramienta peteciones HTTP

# ejecutamos desde otro usuario
RUN addgroup -g 1001 -S appgroup && \
        adduser -S -u 10001 -G appgroup appuser # crear grupo y usuario para ejecutar
USER appuser

WORKDIR /mainApp
# copiamos archivo compilado
COPY --from=builder /mainApp/app/target/*.jar app.jar

# monitoreo, chequea cada 30s, tarda mas 3s = fallo
HEALTHCHECK --interval=30s --timeout=3s \
  CMD curl -f http://localhost:8081/actuator/health || exit 1

ENTRYPOINT ["java", "-jar"]
CMD ["app.jar"]