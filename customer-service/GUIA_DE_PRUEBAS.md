# 🧪 GUÍA DE PRUEBAS - CUSTOMER SERVICE

## 🚀 CÓMO EJECUTAR EL PROYECTO PARA PRUEBAS

### Opción 1: Desde IntelliJ IDEA (Recomendado)
1. Abre el proyecto en IntelliJ
2. Ve a `CustomerServiceApplication.java`
3. Click derecho → **Edit Configurations**
4. En **VM options** agrega: `-Dspring.profiles.active=test`
5. En **Program arguments** agrega: `--server.port=8081`
6. Click **Apply** y **OK**
7. Ejecuta la aplicación (botón verde ▶️)

### Opción 2: Desde Maven
```bash
cd customer-service
mvn spring-boot:run -Dspring-boot.run.arguments="--spring.profiles.active=test --server.port=8081"
```

### Opción 3: Desde CMD/PowerShell
```bash
cd customer-service
mvn clean package
java -jar target/customer-service-0.0.1-SNAPSHOT.jar --spring.profiles.active=test --server.port=8081
```

---

## ✅ VERIFICAR QUE ESTÁ FUNCIONANDO

### 1. Consola - Busca estos logs:
```
🚀 Iniciando carga de datos de prueba...
✅ Estados creados: Eliminado (0), Activo (1), Inactivo (2)
✅ Tipos de persona creados: Natural (1), Jurídica (2)
...
🎉 ¡Carga de datos completada exitosamente!
📊 Resumen:
   - 3 Estados
   - 2 Tipos de Persona
   - 3 Tipos de Identificación
   - 3 Identificaciones
   - 3 Tipos de Cliente
   - 3 Tipos de Dirección
   - 1 Departamentos
   - 1 Provincias
   - 3 Distritos
   - 3 Clientes
   - 3 Contactos
   - 3 Direcciones
```

### 2. Ver la Base de Datos H2
Abre en tu navegador: **http://localhost:8081/h2-console**

**Credenciales:**
- JDBC URL: `jdbc:h2:mem:testdb`
- User Name: `sa`
- Password: (dejar vacío)

Click en **Connect** y podrás ver todas las tablas con datos.

---

## 📡 PROBAR LAS APIs CON POSTMAN

### 1️⃣ Obtener Todos los Clientes
```
GET http://localhost:8081/clients
```

**Respuesta esperada (200 OK):**
```json
[
  {
    "client_id": 1,
    "client_name": "Juan",
    "client_last_name": "Pérez García",
    "registration_date": "2025-10-13T10:30:00",
    "client_type_id": 1,
    "client_type_name": "Regular",
    "identification_id": 1,
    "identification_doc": "72345678",
    "state_entity_id": 1,
    "state_entity_name": "Activo"
  },
  ...
]
```

---

### 2️⃣ Obtener Cliente por ID
```
GET http://localhost:8081/clients/1
```

**Respuesta esperada (200 OK):** Un solo cliente

---

### 3️⃣ Crear un Nuevo Cliente
```
POST http://localhost:8081/clients
Content-Type: application/json

{
  "client_name": "Carlos",
  "client_last_name": "Rodríguez",
  "client_type_id": 1,
  "identification_id": 1
}
```

**Respuesta esperada (201 Created):**
```json
{
  "client_id": 4,
  "client_name": "Carlos",
  "client_last_name": "Rodríguez",
  "registration_date": "2025-10-13T11:45:30",
  "client_type_id": 1,
  "client_type_name": "Regular",
  "identification_id": 1,
  "identification_doc": "72345678",
  "state_entity_id": 1,
  "state_entity_name": "Activo"
}
```

---

### 4️⃣ Actualizar un Cliente
```
PUT http://localhost:8081/clients/1
Content-Type: application/json

{
  "client_name": "Juan Carlos",
  "client_last_name": "Pérez García",
  "client_type_id": 2,
  "identification_id": 1
}
```

**Respuesta esperada (200 OK):** Cliente actualizado

---

### 5️⃣ Eliminar un Cliente (Soft Delete)
```
DELETE http://localhost:8081/clients/1
```

**Respuesta esperada (204 No Content):** Sin cuerpo de respuesta

---

### 6️⃣ Obtener Todos los Contactos
```
GET http://localhost:8081/contacts
```

**Respuesta esperada (200 OK):**
```json
[
  {
    "contact_id": 1,
    "client_id": 1,
    "client_name": "Juan",
    "phone_number": "987654321",
    "email": "juan.perez@email.com",
    "state_entity_id": 1,
    "state_entity_name": "Activo"
  },
  ...
]
```

---

### 7️⃣ Crear un Nuevo Contacto
```
POST http://localhost:8081/contacts
Content-Type: application/json

{
  "client_id": 1,
  "phone_number": "999888777",
  "email": "nuevo.contacto@email.com"
}
```

**Respuesta esperada (201 Created):** Contacto creado

---

### 8️⃣ Obtener Todas las Direcciones
```
GET http://localhost:8081/directions
```

**Respuesta esperada (200 OK):**
```json
[
  {
    "direction_id": 1,
    "client_id": 1,
    "client_name": "Juan",
    "direction_type_id": 1,
    "direction_type_description": "Casa",
    "direction_name": "Av. Larco",
    "direction_number": "1234",
    "reference": "Cerca al Parque Kennedy",
    "district_id": 1,
    "district_name": "Miraflores",
    "province_id": 1,
    "province_name": "Lima",
    "department_id": 1,
    "department_name": "Lima",
    "state_entity_id": 1,
    "state_entity_name": "Activo"
  },
  ...
]
```

---

### 9️⃣ Crear Dirección con Ubicación Nueva (Lógica Automática)
```
POST http://localhost:8081/directions
Content-Type: application/json

{
  "client_id": 1,
  "direction_type_id": 1,
  "direction_name": "Calle Los Pinos",
  "direction_number": "456",
  "reference": "Frente al parque",
  "department_name": "Arequipa",
  "province_name": "Arequipa",
  "district_name": "Yanahuara"
}
```

**Lo que hace el sistema:**
1. ✅ Busca departamento "Arequipa" → NO existe → **Lo CREA**
2. ✅ Busca provincia "Arequipa" → NO existe → **La CREA**
3. ✅ Busca distrito "Yanahuara" → NO existe → **Lo CREA**
4. ✅ Crea la dirección

**Respuesta esperada (201 Created):** Dirección creada con nuevos departamento, provincia y distrito

---

### 🔟 Probar Validaciones (Error 400)
```
POST http://localhost:8081/clients
Content-Type: application/json

{
  "client_name": "",
  "client_last_name": "Test"
}
```

**Respuesta esperada (400 Bad Request):**
```json
{
  "title": "Error de Validación",
  "message": "Errores de validación en los datos proporcionados",
  "errors": [
    {
      "field": "client_name",
      "message": "El nombre del cliente no debe estar vacío"
    },
    {
      "field": "client_type_id",
      "message": "El ID del Tipo de Cliente no debe ser nulo"
    },
    {
      "field": "identification_id",
      "message": "El ID de la Identificación no debe ser nulo"
    }
  ]
}
```

---

## 📋 COLECCIÓN DE POSTMAN

Crea una colección con estas peticiones para pruebas rápidas:

### Variables de Entorno:
- `base_url`: `http://localhost:8081`

### Estructura de Carpetas:
```
Customer Service Tests
├── Clients
│   ├── GET All Clients
│   ├── GET Client by ID
│   ├── POST Create Client
│   ├── PUT Update Client
│   └── DELETE Delete Client
├── Contacts
│   ├── GET All Contacts
│   ├── GET Contact by ID
│   ├── POST Create Contact
│   ├── PUT Update Contact
│   └── DELETE Delete Contact
└── Directions
    ├── GET All Directions
    ├── GET Direction by ID
    ├── POST Create Direction (Existing Location)
    ├── POST Create Direction (New Location)
    ├── PUT Update Direction
    └── DELETE Delete Direction
```

---

## 🐛 SOLUCIÓN DE PROBLEMAS

### Error: "Port 8081 is already in use"
**Solución:** Cambia el puerto en el comando:
```bash
--server.port=8082
```

### Error: "Cannot resolve table"
**Solución:** Las tablas se crean automáticamente. Verifica en H2 Console.

### Error: "EntityNotFoundException"
**Solución:** Asegúrate de usar los IDs correctos de los datos de prueba cargados.

### No se cargan los datos de prueba
**Solución:** Verifica que usaste `--spring.profiles.active=test`

---

## 📊 DATOS DE PRUEBA DISPONIBLES

### Estados
- ID 0: Eliminado
- ID 1: Activo
- ID 2: Inactivo

### Tipos de Persona
- ID 1: Natural
- ID 2: Jurídica

### Tipos de Identificación
- ID 1: DNI (Natural)
- ID 2: RUC (Jurídica)
- ID 3: Pasaporte (Natural)

### Identificaciones
- ID 1: 72345678 (DNI)
- ID 2: 87654321 (DNI)
- ID 3: 20123456789 (RUC)

### Tipos de Cliente
- ID 1: Regular
- ID 2: Premium
- ID 3: VIP

### Tipos de Dirección
- ID 1: Casa
- ID 2: Oficina
- ID 3: Trabajo

### Clientes
- ID 1: Juan Pérez García (DNI: 72345678, Tipo: Regular)
- ID 2: María López Fernández (DNI: 87654321, Tipo: Premium)
- ID 3: Empresa XYZ S.A.C. (RUC: 20123456789, Tipo: VIP)

### Ubicaciones
- Departamento Lima (ID: 1)
  - Provincia Lima (ID: 1)
    - Miraflores (ID: 1)
    - San Isidro (ID: 2)
    - Surco (ID: 3)

