# 🔧 SOLUCIÓN: Error java.lang.ExceptionInInitializerError

## ❌ ERROR:
```
java: java.lang.ExceptionInInitializerError
com.sun.tools.javac.code.TypeTag :: UNKNOWN
```

## 🎯 CAUSA:
Este error ocurre por **incompatibilidad entre versiones de Java**. Tu proyecto usa **Java 21** pero IntelliJ puede estar usando una versión diferente para compilar.

---

## ✅ SOLUCIÓN RÁPIDA (3 métodos)

### 🔵 MÉTODO 1: Configurar Java en IntelliJ (RECOMENDADO)

#### Paso 1: Verificar Java del Proyecto
```
1. En IntelliJ: File → Project Structure (Ctrl + Alt + Shift + S)
2. En "Project Settings" → "Project":
   - Project SDK: Selecciona "21" (Oracle OpenJDK 21 o similar)
   - Si no está Java 21, click en "Download JDK..." → Descarga Java 21
   - Project language level: "21 - Record patterns, pattern matching for switch"
3. Click en "Apply"
```

#### Paso 2: Configurar Módulos
```
1. Todavía en Project Structure
2. Ve a "Modules" → Selecciona "customer-service"
3. En la pestaña "Dependencies":
   - Module SDK: Debería decir "<Project SDK> (21)"
4. Click en "Apply"
```

#### Paso 3: Configurar Compilador Java
```
1. File → Settings (Ctrl + Alt + S)
2. Build, Execution, Deployment → Compiler → Java Compiler
3. En "Project bytecode version": Selecciona "21"
4. En la tabla de abajo, verifica que "customer-service" tenga "21"
5. Click en "Apply" → "OK"
```

#### Paso 4: Invalidar Caché y Recompilar
```
1. File → Invalidate Caches...
2. Marca todas las opciones
3. Click en "Invalidate and Restart"
4. Espera a que IntelliJ reinicie
5. Build → Rebuild Project
```

---

### 🔵 MÉTODO 2: Cambiar a Java 17 (Si no tienes Java 21)

Si no puedes instalar Java 21, cambia el proyecto a Java 17:

#### Paso 1: Modificar pom.xml
```xml
<properties>
    <maven.compiler.source>17</maven.compiler.source>
    <maven.compiler.target>17</maven.compiler.target>
    <java.version>17</java.version>
    <project.build.sourceEncoding>UTF-8</project.build.sourceEncoding>
</properties>
```

#### Paso 2: Configurar IntelliJ para Java 17
```
1. File → Project Structure
2. Project SDK: Selecciona "17"
3. Project language level: "17 - Sealed types, always-strict floating-point"
4. Apply → OK
```

#### Paso 3: Recargar Maven
```
1. Click derecho en pom.xml
2. Maven → Reload Project
3. Build → Rebuild Project
```

---

### 🔵 MÉTODO 3: Usar el compilador de IntelliJ (No Maven)

Si los métodos anteriores no funcionan:

```
1. File → Settings → Build, Execution, Deployment → Build Tools → Maven → Runner
2. Desmarca "Delegate IDE build/run actions to Maven"
3. Apply → OK
4. Build → Rebuild Project
```

---

## 🧪 VERIFICAR LA SOLUCIÓN

Después de aplicar cualquier método:

### 1. Verificar versión de Java en terminal de IntelliJ:
```
View → Tool Windows → Terminal
```

En la terminal escribe:
```cmd
java -version
```

Debería mostrar:
```
java version "21.x.x" o "17.x.x"
```

### 2. Ejecutar la aplicación:
```
1. Click derecho en CustomerServiceApplication.java
2. Run 'CustomerServiceApplication.main()'
```

### 3. Si inicia correctamente, verás:
```
  .   ____          _            __ _ _
 /\\ / ___'_ __ _ _(_)_ __  __ _ \ \ \ \
( ( )\___ | '_ | '_| | '_ \/ _` | \ \ \ \
 \\/  ___)| |_)| | | | | || (_| |  ) ) ) )
  '  |____| .__|_| |_|_| |_\__, | / / / /
 =========|_|==============|___/=/_/_/_/

Started CustomerServiceApplication in X.XXX seconds
```

### 4. Probar en el navegador:
```
http://localhost:8098/clients
```

---

## 📊 TABLA DE COMPATIBILIDAD

| Spring Boot | Java Mínima | Java Recomendada |
|-------------|-------------|------------------|
| 3.x         | 17          | 21               |
| 2.7.x       | 8           | 17               |

Tu proyecto usa Spring Boot 3.x, por eso necesita Java 17 como mínimo.

---

## 🐛 OTROS ERRORES COMUNES

### Error: "Release version 21 not supported"
**Solución:** No tienes Java 21 instalado. Usa el Método 2 para cambiar a Java 17.

### Error: "Lombok requires enabled annotation processing"
**Solución:**
```
1. File → Settings
2. Build, Execution, Deployment → Compiler → Annotation Processors
3. Marca "Enable annotation processing"
4. Apply → OK
```

### Error: "Cannot resolve symbol 'lombok'"
**Solución:**
```
1. Click derecho en pom.xml
2. Maven → Reload Project
3. Espera a que descargue las dependencias
```

### Error: "Port 8098 is already in use"
**Solución:**
```
1. Cierra otros servicios en el puerto 8098
2. O cambia el puerto en Program arguments:
   --spring.profiles.active=test --server.port=8099
```

---

## 💾 DESCARGAR JAVA 21 (Si necesitas)

### Windows:
1. Ve a: https://www.oracle.com/java/technologies/downloads/#java21
2. Descarga: "Windows x64 Installer"
3. Ejecuta el instalador
4. Reinicia IntelliJ
5. File → Project Structure → Project SDK → Add SDK → JDK → Busca la carpeta donde se instaló

### Alternativa - Descargar desde IntelliJ:
```
1. File → Project Structure
2. Project SDK → Add SDK → Download JDK...
3. Version: 21
4. Vendor: Oracle OpenJDK
5. Click en "Download"
6. Espera a que termine
7. Apply → OK
```

---

## ✅ CHECKLIST DE VERIFICACIÓN

Después de aplicar la solución, verifica:

- [ ] `java -version` muestra Java 17 o 21
- [ ] Project Structure → Project SDK está en 17 o 21
- [ ] Settings → Java Compiler → Project bytecode version está en 17 o 21
- [ ] Maven reload completado sin errores
- [ ] Build → Rebuild Project sin errores
- [ ] Aplicación inicia sin errores
- [ ] `http://localhost:8098/clients` funciona

---

## 🎯 RESUMEN RÁPIDO

**Si tienes Java 21 instalado:**
1. File → Project Structure → Project SDK: 21
2. Settings → Java Compiler → Bytecode: 21
3. File → Invalidate Caches → Restart
4. Build → Rebuild Project

**Si solo tienes Java 17:**
1. Cambia pom.xml: `<java.version>17</java.version>`
2. File → Project Structure → Project SDK: 17
3. Maven → Reload Project
4. Build → Rebuild Project

**Si nada funciona:**
1. Settings → Maven → Runner → Desmarca "Delegate IDE build"
2. Build → Rebuild Project

---

## 📞 ¿SIGUE SIN FUNCIONAR?

Si después de todos estos pasos sigue el error:

1. Captura una imagen del error completo
2. Ve a: Help → About → Copy
3. Pega la información (muestra tu versión de IntelliJ y Java)
4. Comparte el error completo de la consola

---

## 🎉 ¡LISTO!

Una vez resuelto el error, continúa con la **GUIA-RAPIDA-INTELLIJ.md** para probar el servicio.

