# 🚀 GUÍA RÁPIDA - EJECUTAR EN INTELLIJ (5 MINUTOS)

## 📋 REQUISITOS
- ✅ IntelliJ IDEA instalado (Community o Ultimate)
- ✅ Java 17 o superior instalado

---

## 🎯 PASOS RÁPIDOS

### 1️⃣ Abrir el proyecto (1 minuto)
```
IntelliJ IDEA → File → Open
Navega a: backend-carpinteria-cubas\customer-service
Click en OK
```

### 2️⃣ Esperar sincronización (2-3 minutos)
- Verás en la parte inferior derecha: "Maven projects need to be imported"
- **Click en "Import Changes"** o **"Enable Auto-Import"**
- Espera a que la barra de progreso termine
- ✅ Cuando termine, verás "Build completed successfully"

### 3️⃣ Ejecutar por primera vez (30 segundos)
```
1. Panel izquierdo → src/main/java/com/content/customer_service/
2. Click en CustomerServiceApplication.java
3. Click derecho → Run 'CustomerServiceApplication.main()'
```

### 4️⃣ Configurar parámetros (1 minuto)
```
1. Arriba, click en el menú "CustomerServiceApplication" ▼
2. Click en "Edit Configurations..."
3. En "Program arguments" pega:
   --spring.profiles.active=test --server.port=8098
4. Click "Apply" → "OK"
5. Click en el botón verde ▶️ Run
```

### 5️⃣ Verificar (30 segundos)
Abre tu navegador:
```
http://localhost:8098/clients
```

✅ **Si ves un JSON con 3 clientes, ¡FUNCIONA PERFECTAMENTE!** 🎉

---

## 📸 REFERENCIA VISUAL

### Consola exitosa debe mostrar:
```
  .   ____          _            __ _ _
 /\\ / ___'_ __ _ _(_)_ __  __ _ \ \ \ \
( ( )\___ | '_ | '_| | '_ \/ _` | \ \ \ \
 \\/  ___)| |_)| | | | | || (_| |  ) ) ) )
  '  |____| .__|_| |_|_| |_\__, | / / / /
 =========|_|==============|___/=/_/_/_/

🎉 ¡Carga de datos completada exitosamente!
📊 Resumen:
   - 3 Clientes
   - 3 Contactos
   - 3 Direcciones

Started CustomerServiceApplication in 8.543 seconds
```

---

## 🧪 PROBAR LAS APIs

### En el navegador:
```
http://localhost:8098/clients          → Ver todos los clientes
http://localhost:8098/contacts         → Ver todos los contactos
http://localhost:8098/directions       → Ver todas las direcciones
http://localhost:8098/h2-console       → Consola de base de datos
```

### Con Postman:
Importa esta colección o prueba manualmente:

#### GET - Ver todos los clientes
```
GET http://localhost:8098/clients
```

#### GET - Ver un cliente específico
```
GET http://localhost:8098/clients/1
```

#### POST - Crear un nuevo cliente
```
POST http://localhost:8098/clients
Content-Type: application/json

{
  "client_name": "Carlos",
  "client_last_name": "Mendoza",
  "client_type_id": 1,
  "identification_id": 1
}
```

#### PUT - Actualizar cliente
```
PUT http://localhost:8098/clients/1
Content-Type: application/json

{
  "client_name": "Juan Actualizado",
  "client_last_name": "Pérez Actualizado",
  "client_type_id": 1,
  "identification_id": 1
}
```

#### DELETE - Eliminar cliente
```
DELETE http://localhost:8098/clients/1
```

---

## 🎯 CARACTERÍSTICAS ESPECIALES

### 🌍 Crear Dirección (CON LÓGICA AUTOMÁTICA)
```
POST http://localhost:8098/directions
Content-Type: application/json

{
  "client_id": 1,
  "direction_type_id": 1,
  "direction_name": "Av. Nueva",
  "direction_number": "999",
  "reference": "Cerca al parque",
  "department_name": "Arequipa",
  "province_name": "Arequipa",
  "district_name": "Cayma"
}
```

✨ **Magia:** Si Arequipa > Arequipa > Cayma no existe en la BD, se crea automáticamente.

---

## 🗄️ VER LA BASE DE DATOS H2

```
URL: http://localhost:8098/h2-console

JDBC URL: jdbc:h2:mem:testdb
Username: sa
Password: (dejar vacío)

Click en "Connect"
```

Puedes ejecutar consultas SQL:
```sql
SELECT * FROM clients;
SELECT * FROM contacts;
SELECT * FROM directions;
SELECT * FROM departments;
```

---

## 🐛 SOLUCIÓN DE PROBLEMAS

### ❌ "Cannot resolve symbol 'springframework'"
**Solución:**
1. Click derecho en `pom.xml`
2. Maven → Reload Project
3. Espera a que termine

### ❌ "Port 8098 is already in use"
**Solución:**
Cambia el puerto en Program arguments:
```
--spring.profiles.active=test --server.port=8099
```

### ❌ No se cargan los 3 clientes de prueba
**Solución:**
Verifica que en Program arguments tengas:
```
--spring.profiles.active=test
```
(Sin esto, usa la BD PostgreSQL y no carga datos de prueba)

### ❌ Error de compilación
**Solución:**
```
IntelliJ: Build → Rebuild Project
```

---

## 📚 ARCHIVOS DE DOCUMENTACIÓN

1. **EJECUTAR-SIN-MAVEN.md** - Guía completa con 3 métodos
2. **GUIA_DE_PRUEBAS.md** - Todos los endpoints con ejemplos
3. **DOCUMENTACION_COMPLETA.md** - Arquitectura y flujo del sistema
4. **README.md** - Características del servicio

---

## ✅ CHECKLIST DE VERIFICACIÓN

Marca cuando completes cada paso:

- [ ] Proyecto abierto en IntelliJ
- [ ] Dependencias Maven descargadas
- [ ] Aplicación ejecutándose sin errores
- [ ] `http://localhost:8098/clients` muestra 3 clientes
- [ ] Puedes crear un nuevo cliente con Postman
- [ ] H2 Console funciona correctamente
- [ ] Puedes crear direcciones con lógica automática

---

## 🎉 ¡LISTO!

Si completaste todos los pasos, tu microservicio está funcionando perfectamente.

**Próximos pasos:**
1. Probar todos los endpoints con Postman
2. Revisar la lógica de direcciones automáticas
3. Explorar los datos en H2 Console
4. Integrar con el frontend

**¿Dudas?** Revisa los otros archivos de documentación. 📖

