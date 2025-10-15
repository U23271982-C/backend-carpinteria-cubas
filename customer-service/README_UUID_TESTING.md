# 🛡️ API con UUIDs - Guía de Pruebas Completa

## ✅ ¿Qué se ha implementado?

### 🔧 **Modelos Actualizados con UUID**
- ✅ Todos los modelos tienen UUID público + ID interno
- ✅ UUID se genera automáticamente con `@PrePersist`
- ✅ ID interno NUNCA se expone en la API

### 🔍 **Repositorios con Búsqueda UUID**
- ✅ `findByUuid()` - Búsqueda principal por UUID
- ✅ `findActiveByUuid()` - Solo registros activos
- ✅ Métodos de búsqueda por relaciones UUID

### 🏗️ **Servicios Completamente Refactorizados**
- ✅ CRUD completo usando solo UUIDs
- ✅ Validaciones de existencia por UUID
- ✅ Soft delete manteniendo seguridad

### 🌐 **API REST con Rutas UUID**
```
GET    /api/v1/clients                    - Todos los clientes
POST   /api/v1/clients                    - Crear cliente
GET    /api/v1/clients/{uuid}             - Cliente por UUID
PUT    /api/v1/clients/{uuid}             - Actualizar cliente
DELETE /api/v1/clients/{uuid}             - Eliminar cliente
GET    /api/v1/clients/search/name?q={}   - Buscar por nombre
```

---

## 🚀 **Pasos para Testear**

### 1️⃣ **Migrar Base de Datos**
```sql
-- Ejecutar el archivo uuid_migration.sql en tu BD
-- Esto agregará columnas UUID y datos de prueba
```

### 2️⃣ **Compilar y Ejecutar**
```bash
cd customer-service
mvn clean compile
mvn spring-boot:run
```

### 3️⃣ **Probar los Endpoints**

## 📋 **Ejemplos de Prueba con Postman/cURL**

### 🔸 **1. Obtener todos los clientes**
```bash
GET http://localhost:8080/api/v1/clients
```

### 🔸 **2. Crear un cliente nuevo**
```bash
POST http://localhost:8080/api/v1/clients
Content-Type: application/json

{
  "clientName": "Juan Carlos",
  "clientLastName": "Pérez García",
  "clientTypeUuid": "UUID-DEL-TIPO-CLIENTE",
  "identificationUuid": "UUID-DE-LA-IDENTIFICACION", 
  "stateEntityUuid": "UUID-DEL-ESTADO-ACTIVO"
}
```

### 🔸 **3. Obtener cliente por UUID**
```bash
GET http://localhost:8080/api/v1/clients/{UUID-DEL-CLIENTE}
```

### 🔸 **4. Actualizar cliente**
```bash
PUT http://localhost:8080/api/v1/clients/{UUID-DEL-CLIENTE}
Content-Type: application/json

{
  "clientName": "Juan Carlos Actualizado",
  "clientLastName": "Pérez García",
  "clientTypeUuid": "UUID-DEL-TIPO-CLIENTE",
  "identificationUuid": "UUID-DE-LA-IDENTIFICACION",
  "stateEntityUuid": "UUID-DEL-ESTADO-ACTIVO"
}
```

### 🔸 **5. Buscar por nombre**
```bash
GET http://localhost:8080/api/v1/clients/search/name?q=Juan
```

### 🔸 **6. Eliminar cliente (soft delete)**
```bash
DELETE http://localhost:8080/api/v1/clients/{UUID-DEL-CLIENTE}
```

---

## 🔒 **Beneficios de Seguridad Implementados**

### ✅ **Sin Exposición de IDs Internos**
- Los UUIDs son imposibles de adivinar
- No hay riesgo de enumeración secuencial
- Los atacantes no pueden "escanear" tus recursos

### ✅ **Desacoplamiento Total**
- Tu API es independiente de la base de datos
- Puedes cambiar el motor de BD sin romper la API
- Los clientes nunca dependen de IDs internos

### ✅ **Escalabilidad para Microservicios**
- UUIDs únicos globalmente
- No hay conflictos entre servicios
- Distribución segura de identificadores

---

## 🧪 **Ejemplo de Respuesta de la API**

### Respuesta típica (SOLO UUIDs expuestos):
```json
{
  "uuid": "a1b2c3d4-e5f6-7890-abcd-ef1234567890",
  "clientName": "Juan Carlos",
  "clientLastName": "Pérez García", 
  "registrationDate": "2024-01-15T10:30:00",
  "clientTypeUuid": "b2c3d4e5-f6g7-8901-bcde-f23456789012",
  "clientTypeName": "Cliente Individual",
  "identificationUuid": "c3d4e5f6-g7h8-9012-cdef-345678901234",
  "identificationNumber": "12345678",
  "identificationTypeName": "DNI",
  "stateEntityUuid": "d4e5f6g7-h8i9-0123-def4-456789012345",
  "stateName": "ACTIVO"
}
```

### ⚠️ **Nota Importante**: 
- NUNCA verás `client_id: 1` en las respuestas
- Solo UUIDs como `uuid: "a1b2c3d4-e5f6-7890-abcd-ef1234567890"`
- Máxima seguridad y profesionalismo

---

## 🎯 **Próximos Pasos Recomendados**

1. **Ejecutar migración SQL**: Aplica `uuid_migration.sql`
2. **Probar endpoints**: Usa los ejemplos de arriba
3. **Implementar en otros servicios**: Replica el patrón UUID
4. **Documentar con Swagger**: Agrega documentación OpenAPI
5. **Tests unitarios**: Crear tests para validar UUIDs

¡Tu API ahora es completamente segura y profesional! 🚀
