# 🔧 SOLUCIÓN DEFINITIVA - Error TypeTag UNKNOWN

## ❌ TU ERROR:
```
java: java.lang.ExceptionInInitializerError
com.sun.tools.javac.code.TypeTag :: UNKNOWN
```

## 🎯 CAUSA REAL:
Tienes **Java 24** instalado, pero el proyecto necesita **Java 17**. IntelliJ está usando Java 24 para compilar, lo que causa incompatibilidad.

---

## ✅ SOLUCIÓN DEFINITIVA (Elige UNA opción)

---

## 🟢 OPCIÓN 1: Descargar Java 17 desde IntelliJ (LA MÁS FÁCIL - 3 minutos)

### Paso 1: Abrir configuración
```
1. En IntelliJ: Presiona Ctrl + Alt + Shift + S
   O ve a: File → Project Structure
```

### Paso 2: Descargar Java 17
```
2. En la sección "Project":
   - Click en "Project SDK" (arriba)
   - Click en el menú desplegable
   - Click en "Download JDK..."
```

### Paso 3: Configurar descarga
```
3. En la ventana emergente:
   - Version: 17
   - Vendor: Eclipse Temurin (u Oracle OpenJDK)
   - Location: (dejar por defecto)
   - Click en "Download"
```

### Paso 4: Esperar descarga
```
4. Espera 1-2 minutos mientras descarga
   Verás una barra de progreso
```

### Paso 5: Configurar proyecto
```
5. Una vez descargado:
   - Project SDK: Debería mostrar "temurin-17" o "17"
   - Project language level: "17 - Sealed types..."
   - Click en "Apply"
```

### Paso 6: Configurar compilador
```
6. En la misma ventana, ve a:
   Modules → customer-service → Dependencies
   - Module SDK: <Project SDK> (17)
   - Click en "Apply" → "OK"
```

### Paso 7: Configurar bytecode
```
7. Presiona Ctrl + Alt + S (o File → Settings)
8. Ve a: Build, Execution, Deployment → Compiler → Java Compiler
9. Project bytecode version: 17
10. Click en "Apply" → "OK"
```

### Paso 8: Limpiar y recompilar
```
11. Build → Rebuild Project
12. Espera a que termine (30-60 segundos)
```

### Paso 9: Invalidar caché
```
13. File → Invalidate Caches...
14. Marca todas las opciones
15. Click en "Invalidate and Restart"
```

### Paso 10: ¡EJECUTAR!
```
16. Después del reinicio:
    - Abre: CustomerServiceApplication.java
    - Click derecho → Run 'CustomerServiceApplication'
    - Agrega argumentos: --spring.profiles.active=test --server.port=8098
    - ¡Debería funcionar! 🎉
```

---

## 🟢 OPCIÓN 2: Descargar Java 17 de Adoptium (Manual - 5 minutos)

### Paso 1: Descargar
```
1. Ve a: https://adoptium.net/es/temurin/releases/
2. Configura:
   - Version: 17 (LTS)
   - Operating System: Windows
   - Architecture: x64
   - Package Type: JDK
3. Click en el botón azul de descarga (.msi)
```

### Paso 2: Instalar
```
4. Ejecuta el archivo descargado (OpenJDK17-jdk_x64_windows_hotspot_17.x.x.msi)
5. Next → Acepta términos → Next
6. IMPORTANTE: Marca "Add to PATH"
7. Next → Install
8. Espera a que termine
9. Finish
```

### Paso 3: Verificar instalación
```
10. Abre un CMD NUEVO (importante que sea nuevo)
11. Ejecuta: java -version
12. Deberías ver: "openjdk version 17.x.x"
```

### Paso 4: Configurar en IntelliJ
```
13. Cierra y abre IntelliJ (para que detecte la nueva instalación)
14. File → Project Structure (Ctrl + Alt + Shift + S)
15. Project SDK → Click en el menú
16. Click en "Add SDK" → "JDK..."
17. Navega a: C:\Program Files\Eclipse Adoptium\jdk-17.x.x
18. Click en "OK"
19. Selecciona ese JDK como Project SDK
20. Apply → OK
```

### Paso 5: Configurar compilador
```
21. Settings (Ctrl + Alt + S)
22. Build, Execution, Deployment → Compiler → Java Compiler
23. Project bytecode version: 17
24. Apply → OK
```

### Paso 6: Recompilar
```
25. Build → Rebuild Project
26. File → Invalidate Caches → Invalidate and Restart
```

---

## 🟢 OPCIÓN 3: Deshabilitar delegación de Maven (Sin descargar nada - 1 minuto)

Esta opción usa el compilador interno de IntelliJ en lugar de Maven.

### Paso 1: Deshabilitar delegación
```
1. File → Settings (Ctrl + Alt + S)
2. Build, Execution, Deployment → Build Tools → Maven → Runner
3. DESMARCA: "Delegate IDE build/run actions to Maven"
4. Apply → OK
```

### Paso 2: Configurar JDK interno
```
5. File → Project Structure (Ctrl + Alt + Shift + S)
6. Project SDK: Si solo tienes Java 24, selecciónalo
7. Modules → Dependencies → Module SDK: <Project SDK>
8. Apply → OK
```

### Paso 3: Cambiar proyecto a Java 21
```
9. Abre el archivo pom.xml
10. Cambia las líneas 18-20 a:
    <maven.compiler.source>21</maven.compiler.source>
    <maven.compiler.target>21</maven.compiler.target>
    <java.version>21</java.version>
```

### Paso 4: Recargar Maven
```
11. Click derecho en pom.xml
12. Maven → Reload Project
```

### Paso 5: Configurar bytecode
```
13. Settings → Compiler → Java Compiler
14. Project bytecode version: 21
15. Apply → OK
```

### Paso 6: Recompilar
```
16. Build → Rebuild Project
17. File → Invalidate Caches → Invalidate and Restart
```

### Paso 7: Ejecutar
```
18. Run CustomerServiceApplication
```

---

## 🎯 ¿CUÁL OPCIÓN ELEGIR?

| Opción | Dificultad | Tiempo | Recomendado para |
|--------|-----------|---------|------------------|
| **Opción 1** | ⭐ Fácil | 3 min | **La mayoría (RECOMENDADO)** |
| **Opción 2** | ⭐⭐ Media | 5 min | Si falla la Opción 1 |
| **Opción 3** | ⭐⭐⭐ Difícil | 1 min | Solo si urge, no es ideal |

**Mi recomendación:** Usa la **OPCIÓN 1** (descargar Java 17 desde IntelliJ). Es la más fácil y rápida.

---

## ✅ VERIFICACIÓN FINAL

Después de aplicar cualquier opción, deberías ver en la consola:

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

Started CustomerServiceApplication in X.XXX seconds (JVM running for X.XXX)
```

Luego abre:
```
http://localhost:8098/clients
```

Deberías ver 3 clientes en JSON. **¡Listo!** 🎉

---

## 🐛 OTROS PROBLEMAS

### Error: "Cannot download JDK"
**Solución:** Tu firewall está bloqueando. Usa la Opción 2 (descarga manual).

### Error: "Lombok requires annotation processing"
**Solución:**
```
Settings → Compiler → Annotation Processors
Marca: "Enable annotation processing"
Apply → OK
```

### Error: "Port 8098 already in use"
**Solución:** Cambia el puerto en Program arguments:
```
--spring.profiles.active=test --server.port=8099
```

---

## 📞 ¿SIGUE SIN FUNCIONAR?

Si después de la **Opción 1** no funciona:

1. Cierra IntelliJ completamente
2. Ve a la carpeta del proyecto
3. Elimina la carpeta: `.idea` (carpeta oculta)
4. Elimina la carpeta: `target`
5. Abre IntelliJ de nuevo
6. File → Open → Selecciona la carpeta customer-service
7. Espera a que sincronice
8. Repite los pasos de la Opción 1

---

## 🎉 RESUMEN ULTRA RÁPIDO

**Para resolver el error en 3 minutos:**

```
1. Ctrl + Alt + Shift + S
2. Project SDK → Download JDK... → Version: 17 → Download
3. Espera 1-2 minutos
4. Apply → OK
5. Ctrl + Alt + S → Compiler → Java Compiler → Bytecode: 17 → OK
6. Build → Rebuild Project
7. File → Invalidate Caches → Restart
8. Run CustomerServiceApplication
9. Abre: http://localhost:8098/clients
10. ¡FUNCIONA! 🎉
```

---

¡Sigue estos pasos y tu error se resolverá! Si tienes algún problema, avísame en qué paso te quedaste. 🚀

