# Customer Service - Microservicio de Gestión de Clientes

## Descripción
Microservicio encargado de la gestión completa de clientes, incluyendo:
- **Clientes**: Información personal y de registro
- **Contactos**: Teléfonos y correos electrónicos de los clientes
- **Direcciones**: Ubicaciones geográficas de los clientes
- **Identificaciones**: Documentos de identidad (DNI, RUC, Pasaporte, etc.)
- **Ubicación Geográfica**: Departamentos, Provincias y Distritos

## Características Principales

### 1. Gestión de Clientes (CRUD completo)
- Crear, listar, obtener, actualizar y eliminar clientes
- Validación de documentos de identificación únicos
- Eliminación lógica (soft delete)

### 2. Gestión de Direcciones con Lógica Inteligente
**Característica especial**: Al registrar una dirección, el sistema automáticamente:
- Verifica si el **departamento** existe en la base de datos
  - Si existe: lo usa
  - Si no existe: lo crea automáticamente
- Verifica si la **provincia** existe dentro del departamento
  - Si existe: la usa
  - Si no existe: la crea automáticamente
- Verifica si el **distrito** existe dentro de la provincia
  - Si existe: lo usa
  - Si no existe: lo crea automáticamente

Esto permite que el frontend solo envíe los nombres de ubicación y el backend se encarga de toda la lógica de gestión de datos geográficos.

### 3. Gestión de Contactos
- Múltiples contactos por cliente
- Validación de emails únicos
- Validación de formato de teléfono

### 4. Validaciones Robustas
- Validación de DTOs con anotaciones Jakarta
- Manejo global de excepciones
- Mensajes de error descriptivos

## Estructura del Proyecto

```
customer-service/
├── src/main/java/com/content/customer_service/
│   ├── controller/          # Controladores REST
│   │   ├── ClientController.java
│   │   ├── ContactController.java
│   │   └── DirectionController.java
│   ├── dto/
│   │   ├── request/         # DTOs de entrada (11 archivos)
│   │   └── response/        # DTOs de salida (11 archivos)
│   ├── exception/           # Manejo de excepciones
│   ├── mapper/              # MapStruct mappers
│   │   ├── convert/         # Interfaces de conversión
│   │   ├── ClientMapper.java
│   │   ├── ContactMapper.java
│   │   ├── DirectionMapper.java
│   │   └── IdentificationMapper.java
│   ├── model/               # Entidades JPA (11 entidades)
│   ├── repository/          # Repositorios JPA (11 repositorios)
│   ├── service/             # Lógica de negocio
│   │   ├── abstractService/ # Interfaces de servicios
│   │   ├── ClientServiceImpl.java
│   │   ├── ContactServiceImpl.java
│   │   └── DirectionServiceImpl.java
│   ├── util/                # Utilidades
│   └── common/              # Constantes
└── src/main/resources/
    └── application.yml      # Configuración
```

## Endpoints Principales

### Clientes
- `POST /clients` - Crear cliente
- `GET /clients` - Listar todos los clientes
- `GET /clients/{id}` - Obtener cliente por ID
- `PUT /clients/{id}` - Actualizar cliente
- `DELETE /clients/{id}` - Eliminar cliente (lógico)

### Direcciones
- `POST /directions` - Crear dirección (con lógica automática de ubicación)
- `GET /directions` - Listar todas las direcciones
- `GET /directions/{id}` - Obtener dirección por ID
- `PUT /directions/{id}` - Actualizar dirección
- `DELETE /directions/{id}` - Eliminar dirección (lógico)

### Contactos
- `POST /contacts` - Crear contacto
- `GET /contacts` - Listar todos los contactos
- `GET /contacts/{id}` - Obtener contacto por ID
- `PUT /contacts/{id}` - Actualizar contacto
- `DELETE /contacts/{id}` - Eliminar contacto (lógico)

## Ejemplo de Uso - Crear Dirección

```json
POST /directions
{
  "client_id": 1,
  "direction_type_id": 1,
  "direction_name": "Av. Arequipa",
  "direction_number": "1234",
  "reference": "Cerca al parque",
  "department_name": "Lima",
  "province_name": "Lima",
  "district_name": "Miraflores"
}
```

El sistema automáticamente:
1. Verifica si existe el departamento "Lima" (si no, lo crea)
2. Verifica si existe la provincia "Lima" en ese departamento (si no, la crea)
3. Verifica si existe el distrito "Miraflores" en esa provincia (si no, lo crea)
4. Crea la dirección asociada al cliente

## Tecnologías Utilizadas
- **Spring Boot 3.x**
- **Spring Data JPA**
- **PostgreSQL**
- **MapStruct** - Mapeo automático de objetos
- **Lombok** - Reducción de código boilerplate
- **Hibernate Validator** - Validación de datos
- **Eureka Client** - Registro en service discovery

## Base de Datos
- Motor: PostgreSQL
- Base de datos: `BDCustomers`
- Puerto: 5432

## Estados de Entidades
- **0**: Eliminado
- **1**: Activo
- **2**: Inactivo

## Compilación y Ejecución

```bash
# Compilar
mvn clean install

# Ejecutar
mvn spring-boot:run
```

## Variables de Entorno
- `SERVER_PORT`: Puerto del servidor (por defecto: configurado en docker-compose)
- `SPRING_DATASOURCE_URL`: URL de la base de datos
- `SPRING_DATASOURCE_USERNAME`: Usuario de la base de datos
- `SPRING_DATASOURCE_PASSWORD`: Contraseña de la base de datos
- `DISCOVERY_SERVER_URL`: URL del servidor Eureka

## Notas Importantes
1. **Eliminación Lógica**: Todas las eliminaciones son lógicas, cambiando el estado a 0
2. **Validaciones**: Todos los DTOs tienen validaciones completas
3. **Logs**: Sistema completo de logging con SLF4J
4. **Transacciones**: Todas las operaciones de escritura son transaccionales
5. **Optimización**: Uso de FetchType.LAZY para optimizar consultas

