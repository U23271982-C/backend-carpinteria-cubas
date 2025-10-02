# Backend Carpintería Cubas

### Nomenclatura:
- *Variables:* Snake_Case
- *Métodos:* camelCase
- *Clases, Enum, Interface:* UpperCamelCase
- *Constantes y variables de entorno:* UPPER_SNAKE_CASE

### Stack de Tecnologías:
- Java 21
- Spring Boot 3.5.5
- Spring Cloud/Eureka Netflix
- Spring Data JPA
- Spring Security
- Spring Web
- Spring Cloud Gateway
- Spring Cloud Config 

---
### Implementar Microservicio Cliente
1. Crear módulo con Maven
2. Extenderlo al módulo padre
3. Implementar /resources/application.yml
4. Implementar Dockerfile
5. Añadir el servicio a 'docker-compose.yml'
6. Añadirlo al mapeo del gateway
    
### API Gateway - Spring Cloud Gateway
```yaml
server:
  port: ${SERVER_PORT}
spring:
  application:
    name: api-gateway

cloud:
  gateway:
    server:
      webflux:
        routes:
          - id: authentication-service
            uri: lb://authentication-service
            predicates:
              - Path=/auth/**
            filters:
              - StripPrefix=1

eureka:
  client:
    instance:
      instance-id: ${spring.application.name}:${spring.application.instance-id}:${random.value}
  service-url:
    defaultZone: ${DISCOVERY_SERVER_URL} #Servidor de Eureka
```
En `api/gateway/src/main/resources/application.yml`
- `id`: Indentificador, para que el Gateway y los logs sepan a qué ruta te refieres. Normalmente se llama igual que el microservicio.
- `uri`: Ubicacaion del microservicio.
  - `lb`: Significa **LoadBalancer**, es decir, que el Gateway no va a un host:puerto fijo, sino que busca el servicio registrado en Eureka o en el Discovery Server. Obligatoriamente se tiene que llamar igual que el 'spring.application.name'. Ejemplo. `lb://authentication-service`
  Microservicio de Authenticación:
  ```yaml
  spring:
    application:
      name: authentication-service
  ```
  por lo que para rutearlo en Gateway se pone `lb://authentication-service`
- `predicates`: Condiciones para que el Gateway sepa que ruta es la que se quiere acceder.
  - `Path`: Indica que la ruta debe coincidir con la ruta que se quiere acceder. El `auth` es el path del microservicio, cada microservicio tiene su propio path.
- `filters`: Filtrado de la ruta.
  - `StripPrefix=1`: Elimina el prefijo de la ruta.