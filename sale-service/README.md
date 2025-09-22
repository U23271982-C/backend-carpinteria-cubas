# Sale Service - Microservicio de Gestión de Ventas

## 📋 Descripción

El **Sale Service** es un microservicio desarrollado en Spring Boot que gestiona las operaciones de ventas en el sistema de carpintería. Proporciona funcionalidades completas para crear, consultar, actualizar y eliminar ventas, incluyendo validaciones de negocio, integración con otros microservicios y cálculos automáticos.

## 🏗️ Arquitectura

### Tecnologías Utilizadas
- **Java 21**
- **Spring Boot 3.x**
- **Spring Data JPA**
- **Spring Cloud OpenFeign** (para comunicación entre microservicios)
- **MySQL 8.0** (Base de datos)
- **Docker & Docker Compose**
- **Maven** (Gestión de dependencias)
- **Lombok** (Reducción de código boilerplate)
- **JUnit 5 & Mockito** (Testing)

### Patrón de Arquitectura
- **Arquitectura de Microservicios**
- **Patrón Repository**
- **Patrón DTO (Data Transfer Object)**
- **Separación por capas (Controller, Service, Repository)**

## 🚀 Características Principales

### Funcionalidades de Negocio
- ✅ **Gestión completa de ventas** (CRUD)
- ✅ **Validación de clientes** (integración con customer-service)
- ✅ **Validación de productos y stock** (integración con product-service)
- ✅ **Cálculo automático de subtotales y totales**
- ✅ **Actualización automática de inventario**
- ✅ **Generación automática de números de venta**
- ✅ **Consultas por cliente**
- ✅ **Estadísticas de ventas**

### Características Técnicas
- ✅ **API RESTful** con documentación OpenAPI
- ✅ **Comunicación asíncrona** con otros microservicios
- ✅ **Manejo de excepciones** personalizado
- ✅ **Validaciones de entrada** robustas
- ✅ **Configuración por perfiles** (dev, test, prod)
- ✅ **Health checks** y monitoreo
- ✅ **Containerización** con Docker
- ✅ **Pruebas unitarias e integración**

## 📁 Estructura del Proyecto

```
sale-service/
├── src/
│   ├── main/
│   │   ├── java/com/content/sale_service/
│   │   │   ├── client/                 # Feign Clients
│   │   │   │   ├── CustomerClient.java
│   │   │   │   └── ProductClient.java
│   │   │   ├── controller/             # Controladores REST
│   │   │   │   └── SaleController.java
│   │   │   ├── dto/                    # Data Transfer Objects
│   │   │   │   ├── Request/
│   │   │   │   ├── Response/
│   │   │   │   └── external/
│   │   │   ├── entity/                 # Entidades JPA
│   │   │   │   ├── Sale.java
│   │   │   │   └── SaleDetail.java
│   │   │   ├── repository/             # Repositorios
│   │   │   │   └── SaleRepository.java
│   │   │   ├── service/                # Lógica de negocio
│   │   │   │   ├── abstractService/
│   │   │   │   └── impl/
│   │   │   └── SaleServiceApplication.java
│   │   └── resources/
│   │       └── application.yml
│   └── test/                           # Pruebas
├── wiremock/                           # Mocks para desarrollo
├── docker-compose.yml
├── Dockerfile
├── pom.xml
└── README.md
```

## 🛠️ Instalación y Configuración

### Prerrequisitos
- Java 21 o superior
- Maven 3.8+
- Docker y Docker Compose
- MySQL 8.0 (opcional, se puede usar con Docker)

### 1. Clonar el Repositorio
```bash
git clone <repository-url>
cd backend-carpinteria-cubas/sale-service
```

### 2. Configuración de Variables de Entorno
Crear un archivo `.env` en la raíz del proyecto:

```env
# Base de datos
SPRING_DATASOURCE_URL=jdbc:mysql://localhost:3306/BDESaleCarprentry
SPRING_DATASOURCE_USERNAME=root
SPRING_DATASOURCE_PASSWORD=root

# Perfil activo
SPRING_PROFILES_ACTIVE=dev

# Puerto del servidor
SERVER_PORT=8083

# Eureka Discovery
DISCOVERY_SERVER_URL=http://localhost:8089/eureka

# URLs de otros microservicios
CUSTOMER_SERVICE_URL=http://localhost:8081
PRODUCT_SERVICE_URL=http://localhost:8082
```

## 🐳 Despliegue con Docker

### Opción 1: Docker Compose (Recomendado)
```bash
# Construir y ejecutar todos los servicios
docker-compose up -d

# Ver logs
docker-compose logs -f sale-service

# Detener servicios
docker-compose down
```

### Opción 2: Docker Manual
```bash
# Construir la imagen
docker build -t sale-service:latest .

# Ejecutar el contenedor
docker run -d \
  --name sale-service \
  -p 8083:8083 \
  -e SPRING_PROFILES_ACTIVE=prod \
  -e SPRING_DATASOURCE_URL=jdbc:mysql://host.docker.internal:3306/BDESaleCarprentry \
  sale-service:latest
```

## 🏃‍♂️ Ejecución Local

### Desarrollo
```bash
# Compilar el proyecto
mvn clean compile

# Ejecutar en modo desarrollo
mvn spring-boot:run -Dspring-boot.run.profiles=dev

# O usando Java directamente
mvn clean package -DskipTests
java -jar target/sale-service-*.jar --spring.profiles.active=dev
```

### Testing
```bash
# Ejecutar todas las pruebas
mvn test

# Ejecutar pruebas con perfil de test
mvn test -Dspring.profiles.active=test

# Generar reporte de cobertura
mvn jacoco:report
```

## 📚 API Documentation

### Endpoints Principales

#### Gestión de Ventas
```http
# Crear nueva venta
POST /api/v1/sales
Content-Type: application/json

{
  "subtotal": 100.00,
  "total": 116.00,
  "client_id": 1,
  "state_sale_current_id": 1,
  "state_entity_current_id": 1,
  "saleDetails": [
    {
      "product_id": 1,
      "quantity": 2,
      "unit_price": 50.00,
      "subtotal": 100.00
    }
  ]
}

# Obtener todas las ventas
GET /api/v1/sales

# Obtener venta por ID
GET /api/v1/sales/{id}

# Actualizar venta
PUT /api/v1/sales/{id}

# Eliminar venta
DELETE /api/v1/sales/{id}
```

#### Endpoints Adicionales
```http
# Health check
GET /api/v1/sales/health

# Ventas por cliente
GET /api/v1/sales/customer/{customerId}

# Estadísticas de ventas
GET /api/v1/sales/stats
```

### Respuestas de la API

#### Respuesta Exitosa
```json
{
  "success": true,
  "message": "Operación exitosa",
  "data": {
    "number_sale": "SALE-20240101-001",
    "date_sale": "2024-01-01",
    "hour_sale": "10:30:00",
    "subtotal": 100.00,
    "total": 116.00,
    "client_id": 1,
    "id_state_sale_current": 1
  },
  "timestamp": "2024-01-01T10:30:00"
}
```

#### Respuesta de Error
```json
{
  "success": false,
  "message": "Error en la operación",
  "error": "Detalles del error",
  "timestamp": "2024-01-01T10:30:00"
}
```

## 🔧 Configuración por Perfiles

### Desarrollo (dev)
- Base de datos: MySQL local (localhost:3306)
- Logging: DEBUG level
- Hot reload habilitado
- Eureka habilitado

### Testing (test)
- Base de datos: H2 en memoria
- Logging: DEBUG level
- Eureka deshabilitado
- Puerto aleatorio

### Producción (prod)
- Base de datos: MySQL containerizado
- Logging: INFO level
- Optimizaciones de rendimiento
- Health checks habilitados

## 🧪 Testing

### Estructura de Pruebas
```
src/test/java/
├── controller/
│   └── SaleControllerTest.java     # Pruebas de integración del controlador
└── service/
    └── SaleServiceImplTest.java    # Pruebas unitarias del servicio
```

### Ejecutar Pruebas
```bash
# Todas las pruebas
mvn test

# Solo pruebas unitarias
mvn test -Dtest="*Test"

# Solo pruebas de integración
mvn test -Dtest="*IT"

# Con cobertura
mvn clean test jacoco:report
```

## 📊 Monitoreo y Observabilidad

### Health Checks
```bash
# Verificar estado del servicio
curl http://localhost:8083/api/v1/sales/health

# Actuator endpoints
curl http://localhost:8083/actuator/health
curl http://localhost:8083/actuator/metrics
```

### Logs
```bash
# Ver logs en tiempo real
docker-compose logs -f sale-service

# Logs en archivo (producción)
tail -f logs/sale-service.log
```

## 🔒 Seguridad

### Mejores Prácticas Implementadas
- ✅ Usuario no-root en contenedor Docker
- ✅ Variables de entorno para configuración sensible
- ✅ Validación de entrada en todos los endpoints
- ✅ Manejo seguro de excepciones
- ✅ Logs sin información sensible

## 🚨 Troubleshooting

### Problemas Comunes

#### Error de Conexión a Base de Datos
```bash
# Verificar que MySQL esté ejecutándose
docker-compose ps

# Verificar logs de la base de datos
docker-compose logs db_mysql_sale

# Reiniciar servicios
docker-compose restart
```

#### Error de Comunicación con Otros Microservicios
```bash
# Verificar que los servicios estén disponibles
curl http://localhost:8081/actuator/health  # customer-service
curl http://localhost:8082/actuator/health  # product-service

# Verificar configuración de Feign
docker-compose logs sale-service | grep -i feign
```

#### Puerto en Uso
```bash
# Verificar qué proceso usa el puerto
lsof -i :8083

# Cambiar puerto en variables de entorno
export SERVER_PORT=8084
```

## 🤝 Contribución

### Guías de Desarrollo
1. Seguir las convenciones de código Java
2. Escribir pruebas para nueva funcionalidad
3. Actualizar documentación cuando sea necesario
4. Usar commits descriptivos

### Proceso de Desarrollo
```bash
# Crear rama para nueva funcionalidad
git checkout -b feature/nueva-funcionalidad

# Desarrollar y probar
mvn clean test

# Commit y push
git add .
git commit -m "feat: agregar nueva funcionalidad"
git push origin feature/nueva-funcionalidad
```

## 📞 Soporte

Para soporte técnico o reportar problemas:
- **Email**: support@carpinteria-cubas.com
- **Issues**: Crear un issue en el repositorio
- **Documentación**: Consultar este README

## 📄 Licencia

Este proyecto está bajo la Licencia MIT. Ver el archivo `LICENSE` para más detalles.

---

**Versión**: 1.0.0  
**Última actualización**: Enero 2024  
**Mantenido por**: Equipo de Desarrollo Carpintería Cubas