# 📚 DOCUMENTACIÓN COMPLETA - CUSTOMER SERVICE

## 🗂️ EXPLICACIÓN DE CARPETAS Y SU FUNCIÓN

### 1. **📦 model/** - Entidades de Base de Datos
**¿Qué hace?** Define las tablas de la base de datos usando JPA/Hibernate
- `Client.java` - Tabla de clientes
- `Contact.java` - Tabla de contactos (teléfonos, emails)
- `Direction.java` - Tabla de direcciones
- `Department.java`, `Province.java`, `District.java` - Ubicaciones geográficas
- `Identification.java` - Documentos de identidad
- `ClientType.java`, `DirectionType.java`, etc. - Tipos/Catálogos

**Anotaciones importantes:**
- `@Entity` - Marca la clase como tabla
- `@Id` - Clave primaria
- `@ManyToOne`, `@OneToMany` - Relaciones entre tablas

---

### 2. **📝 dto/request/** - Datos de Entrada (Frontend → Backend)
**¿Qué hace?** Define qué datos debe enviar el frontend para crear/actualizar registros

**Ejemplo:** `ClientRequestDTO.java`
```java
{
  "client_name": "Juan",           // ✅ Obligatorio
  "client_last_name": "Pérez",     // ✅ Obligatorio
  "client_type_id": 1,              // ✅ Obligatorio (ID del tipo)
  "identification_id": 5            // ✅ Obligatorio (ID de la identificación)
}
```

**NO incluyen:**
- ❌ IDs autogenerados
- ❌ Fechas de registro (se generan automáticamente)
- ❌ Estados (se asignan automáticamente)

---

### 3. **📤 dto/response/** - Datos de Salida (Backend → Frontend)
**¿Qué hace?** Define qué datos devuelve el backend al frontend

**Ejemplo:** `ClientResponseDTO.java`
```json
{
  "client_id": 1,                    // ✅ ID generado
  "client_name": "Juan",
  "client_last_name": "Pérez",
  "registration_date": "2025-10-13T10:30:00",  // ✅ Fecha generada
  "client_type_id": 1,
  "client_type_name": "Regular",     // ✅ Nombre del tipo (JOIN)
  "identification_id": 5,
  "identification_doc": "12345678",  // ✅ Documento (JOIN)
  "state_entity_id": 1,
  "state_entity_name": "Activo"      // ✅ Estado (JOIN)
}
```

---

### 4. **🗄️ repository/** - Acceso a Base de Datos
**¿Qué hace?** Interfaces para consultar la base de datos (Spring Data JPA)

**Métodos automáticos:**
- `findAll()` - Obtener todos
- `findById(id)` - Obtener por ID
- `save(entity)` - Guardar/actualizar
- `deleteById(id)` - Eliminar

**Métodos personalizados:**
```java
// En ClientRepository:
Optional<Client> findByIdentification_id_IdentificationDoc(String doc);
boolean existsByIdentification_id_IdentificationDoc(String doc);
```

---

### 5. **🔄 mapper/** - Conversión de Datos
**¿Qué hace?** Convierte automáticamente entre Modelo ↔ DTO usando MapStruct

**Flujo:**
```
Frontend envía ClientRequestDTO
         ↓
Mapper convierte a Client (Modelo)
         ↓
Se guarda en BD
         ↓
Mapper convierte a ClientResponseDTO
         ↓
Se devuelve al Frontend
```

---

### 6. **⚙️ service/** - Lógica de Negocio
**¿Qué hace?** Contiene toda la lógica de negocio del sistema

**Responsabilidades:**
- ✅ Validar datos
- ✅ Verificar que existan entidades relacionadas
- ✅ Aplicar reglas de negocio
- ✅ Guardar en base de datos
- ✅ Manejar transacciones

**Ejemplo - DirectionServiceImpl:**
```java
create(DirectionRequestDTO dto) {
  1. Validar datos
  2. Verificar que cliente existe
  3. Verificar que tipo de dirección existe
  4. 🌟 BUSCAR O CREAR Departamento (si no existe)
  5. 🌟 BUSCAR O CREAR Provincia (si no existe)
  6. 🌟 BUSCAR O CREAR Distrito (si no existe)
  7. Crear la dirección
  8. Guardar en BD
  9. Devolver respuesta
}
```

---

### 7. **🎮 controller/** - Endpoints REST
**¿Qué hace?** Expone las APIs REST para que el frontend pueda consumirlas

**Ejemplo:**
```java
@PostMapping              → POST /clients
@GetMapping               → GET /clients
@GetMapping("/{id}")      → GET /clients/1
@PutMapping("/{id}")      → PUT /clients/1
@DeleteMapping("/{id}")   → DELETE /clients/1
```

---

### 8. **🚨 exception/** - Manejo de Errores
**¿Qué hace?** Captura y formatea todos los errores del sistema

**Tipos de errores:**
- `EntityNotFoundException` → 404 Not Found
- `IllegalArgumentException` → 400 Bad Request
- `EValidation` → 400 Bad Request (con detalles de validación)

**Respuesta de error:**
```json
{
  "title": "Error de Validación",
  "message": "Errores en los datos proporcionados",
  "errors": [
    {
      "field": "client_name",
      "message": "El nombre no debe estar vacío"
    }
  ]
}
```

---

### 9. **🛠️ util/** - Utilidades
**¿Qué hace?** Funciones auxiliares reutilizables
- `UtilityValidator` - Valida DTOs con anotaciones

---

### 10. **🎯 common/** - Constantes
**¿Qué hace?** Define constantes usadas en todo el proyecto
- `Formatting` - Formatos de fecha/hora

---

## 🔄 FLUJO COMPLETO DE UNA PETICIÓN

### Ejemplo: Crear un Cliente

```
1. FRONTEND envía JSON
   POST http://localhost:8081/clients
   {
     "client_name": "Juan",
     "client_last_name": "Pérez",
     "client_type_id": 1,
     "identification_id": 5
   }

2. CONTROLLER recibe la petición
   ClientController.createClient()
   - Valida con @Valid
   - Llama al servicio

3. SERVICE (Lógica de Negocio)
   ClientServiceImpl.create()
   - Valida datos con UtilityValidator
   - Busca ClientType con ID 1 (¿existe?)
   - Busca Identification con ID 5 (¿existe?)
   - Verifica que no exista otro cliente con ese documento
   - Obtiene el estado "Activo" (ID = 1)
   - Usa MAPPER para convertir DTO → Modelo
   - Asigna relaciones (ClientType, Identification, StateEntity)

4. REPOSITORY guarda en BD
   clientRepository.save(client)

5. MAPPER convierte Modelo → DTO Response
   ClientMapper.toDTO(savedClient)
   - Incluye nombres de relaciones (client_type_name, etc.)

6. CONTROLLER devuelve respuesta
   HTTP 201 Created
   {
     "client_id": 1,
     "client_name": "Juan",
     "client_last_name": "Pérez",
     "registration_date": "2025-10-13T10:30:00",
     "client_type_id": 1,
     "client_type_name": "Regular",
     ...
   }
```

---

## 🌟 LÓGICA ESPECIAL: DIRECCIONES

### ¿Cómo funciona la creación automática de ubicaciones?

```java
Frontend envía:
{
  "client_id": 1,
  "direction_type_id": 1,
  "direction_name": "Av. Arequipa",
  "direction_number": "123",
  "department_name": "Lima",      // 🎯 Solo el NOMBRE
  "province_name": "Lima",        // 🎯 Solo el NOMBRE
  "district_name": "Miraflores"   // 🎯 Solo el NOMBRE
}

Backend hace:
1. ¿Existe departamento "Lima"?
   SÍ  → Usa el existente
   NO  → Lo CREA automáticamente

2. ¿Existe provincia "Lima" en ese departamento?
   SÍ  → Usa la existente
   NO  → La CREA automáticamente

3. ¿Existe distrito "Miraflores" en esa provincia?
   SÍ  → Lo usa
   NO  → Lo CREA automáticamente

4. Crea la dirección con el distrito encontrado/creado
```

**Ventaja:** El frontend no necesita hacer 3 peticiones separadas para crear ubicaciones.

---

## 📊 ESTADOS DE ENTIDADES

Todas las entidades tienen un campo `state_entity_id`:
- **0** = Eliminado (soft delete)
- **1** = Activo
- **2** = Inactivo

---

## 🔗 RELACIONES ENTRE ENTIDADES

```
StateEntity (Estados)
  ↓ (1 a muchos)
  ├── Client
  ├── ClientType
  ├── Contact
  ├── Direction
  ├── Department
  ├── Province
  ├── District
  ├── Identification
  ├── IdentificationType
  ├── DirectionType
  └── PersonType

PersonType (Natural, Jurídica)
  ↓ (1 a muchos)
  IdentificationType (DNI, RUC, Pasaporte)
    ↓ (1 a muchos)
    Identification (Documentos)
      ↓ (1 a muchos)
      Client (Clientes)
        ↓ (1 a muchos)
        ├── Contact (Contactos)
        └── Direction (Direcciones)

Department
  ↓ (1 a muchos)
  Province
    ↓ (1 a muchos)
    District
      ↓ (1 a muchos)
      Direction
```

---

## ✅ VALIDACIONES IMPLEMENTADAS

### ClientRequestDTO
- ✅ Nombre obligatorio, máx 100 caracteres
- ✅ Apellido obligatorio, máx 100 caracteres
- ✅ client_type_id positivo y no nulo
- ✅ identification_id positivo y no nulo

### ContactRequestDTO
- ✅ client_id positivo y no nulo
- ✅ Teléfono obligatorio, máx 20 caracteres, formato válido
- ✅ Email obligatorio, formato email válido

### DirectionRequestDTO
- ✅ client_id positivo y no nulo
- ✅ direction_type_id positivo y no nulo
- ✅ Campos de texto con límites de longitud
- ✅ department_name, province_name, district_name obligatorios

---

## 🎯 NEXT STEPS

Ahora voy a configurar todo para que puedas probarlo sin PostgreSQL usando H2 Database (en memoria).

