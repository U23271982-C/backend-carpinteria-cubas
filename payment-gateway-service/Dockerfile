# ETAPA CONSTRUCCION
# contexto construccion desde la raiz
FROM maven:3.9-eclipse-temurin-21 AS builder

ARG NAME_MICROSERVICE=payment-service

# simula la raiz
WORKDIR /mainApp
# guardamos dependencias padre
COPY ./pom.xml ./pom.xml

# simula carpeta microservicio
WORKDIR /mainApp/app
# guardamos depencias y code
COPY ${NAME_MICROSERVICE}/pom.xml .

RUN mvn dependency:go-offline -B # instalamos dependencias necesarias

COPY ${NAME_MICROSERVICE}/src/ ./src/

RUN mvn clean package -DskipTests # compilamos excluyendo tests

# ETAPA EJECUCION
FROM eclipse-temurin:21-jre-alpine

# ejecutamos desde otro usuario
RUN addgroup -g 1001 -S appgroup && \
        adduser -S -u 10001 -G appgroup appuser # crear grupo y usuario para ejecutar
USER appuser

WORKDIR /mainApp
# copiamos archivo compilado
COPY --from=builder /mainApp/app/target/*.jar app.jar

ENTRYPOINT ["java", "-jar"]
CMD ["app.jar"]