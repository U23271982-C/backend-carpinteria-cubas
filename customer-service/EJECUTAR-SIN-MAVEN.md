# 🚀 CÓMO EJECUTAR EL PROYECTO SIN MAVEN EN WINDOWS

## ✅ MÉTODO 1: USAR INTELLIJ IDEA (RECOMENDADO - MÁS FÁCIL)

### Paso 1: Abrir el proyecto
1. Abre **IntelliJ IDEA**
2. Ve a **File → Open**
3. Navega y selecciona la carpeta: `customer-service`
4. Click en **OK**

### Paso 2: Esperar la sincronización
- IntelliJ automáticamente detectará que es un proyecto Maven
- Verás una notificación en la esquina inferior derecha
- **Espera** a que descargue todas las dependencias (puede tardar 2-5 minutos la primera vez)
- Verás un mensaje "Maven projects need to be imported" → Click en **Import Changes** o **Enable Auto-Import**

### Paso 3: Ejecutar la aplicación
1. En el panel izquierdo, navega a:
   ```
   src → main → java → com → content → customer_service → CustomerServiceApplication.java
   ```

2. **Haz clic derecho** en el archivo `CustomerServiceApplication.java`

3. Selecciona: **Run 'CustomerServiceApplication.main()'**

### Paso 4: Configurar parámetros (IMPORTANTE)
Después de la primera ejecución:

1. Click en el menú desplegable arriba (donde dice "CustomerServiceApplication")
2. Click en **Edit Configurations...**
3. En el campo **Program arguments** pega:
   ```
   --spring.profiles.active=test --server.port=8098
   ```
4. Click en **Apply** → **OK**
5. Click en el botón verde de **Run** ▶️

### Paso 5: Verificar que funciona
Abre tu navegador y ve a:
```
http://localhost:8098/clients
```

Deberías ver un JSON con 3 clientes de prueba. 🎉

---

## ✅ MÉTODO 2: USAR VISUAL STUDIO CODE

Si prefieres VS Code:

### Paso 1: Instalar extensiones
1. Abre VS Code
2. Ve a Extensions (Ctrl + Shift + X)
3. Instala:
   - **Extension Pack for Java** (de Microsoft)
   - **Spring Boot Extension Pack** (de VMware)

### Paso 2: Abrir el proyecto
1. **File → Open Folder**
2. Selecciona la carpeta `customer-service`
3. Espera a que sincronice las dependencias

### Paso 3: Ejecutar
1. Abre `CustomerServiceApplication.java`
2. Verás un botón **Run** o **Debug** encima del método `main`
3. Click en **Run**
4. En la terminal integrada, deberías ver los logs

Para agregar los parámetros:
1. Crea un archivo `.vscode/launch.json` en la carpeta del proyecto
2. Usa esta configuración:
```json
{
    "version": "0.2.0",
    "configurations": [
        {
            "type": "java",
            "name": "Customer Service Test",
            "request": "launch",
            "mainClass": "com.content.customer_service.CustomerServiceApplication",
            "args": "--spring.profiles.active=test --server.port=8098"
        }
    ]
}
```

---

## ✅ MÉTODO 3: INSTALAR MAVEN (OPCIONAL)

Si quieres usar los comandos Maven:

### Descargar Maven:
1. Ve a: https://maven.apache.org/download.cgi
2. Descarga: **apache-maven-3.9.x-bin.zip** (Binary zip archive)
3. Extrae el ZIP en: `C:\Program Files\Apache\maven`

### Configurar variables de entorno:
1. Presiona **Win + X** → **System**
2. Click en **Advanced system settings** → **Environment Variables**
3. En **System variables**, click en **New**:
   - Variable name: `MAVEN_HOME`
   - Variable value: `C:\Program Files\Apache\maven`
4. Edita la variable **Path**:
   - Click en **New**
   - Agrega: `%MAVEN_HOME%\bin`
5. Click en **OK** en todas las ventanas

### Verificar instalación:
Abre un **nuevo CMD** y ejecuta:
```cmd
mvn --version
```

Si ves la versión de Maven, ¡listo! Ahora puedes usar:
```cmd
cd customer-service
mvn spring-boot:run -Dspring-boot.run.arguments="--spring.profiles.active=test --server.port=8098"
```

---

## 📊 VERIFICACIÓN FINAL

Una vez que el servicio esté corriendo (con cualquier método), verifica:

### 1. Ver clientes:
```
http://localhost:8098/clients
```

### 2. Ver contactos:
```
http://localhost:8098/contacts
```

### 3. Ver direcciones:
```
http://localhost:8098/directions
```

### 4. Consola H2 (Base de datos):
```
http://localhost:8098/h2-console
```
- **JDBC URL:** `jdbc:h2:mem:testdb`
- **Username:** `sa`
- **Password:** (dejar vacío)

---

## 🐛 SOLUCIÓN DE PROBLEMAS

### IntelliJ no reconoce el proyecto Maven:
1. Click derecho en el archivo `pom.xml`
2. Selecciona **Add as Maven Project** o **Reload Project**

### Error: "Port 8098 is already in use":
Cambia el puerto en los argumentos:
```
--spring.profiles.active=test --server.port=8099
```

### No se descargan las dependencias:
1. Ve a **File → Settings → Build, Execution, Deployment → Build Tools → Maven**
2. Verifica que "Maven home directory" apunte a la instalación de Maven incluida en IntelliJ
3. Click en **Apply**

### Error de compilación:
En IntelliJ:
1. **Build → Rebuild Project**
2. Espera a que termine
3. Intenta ejecutar de nuevo

---

## 📞 ¿NECESITAS MÁS AYUDA?

Si sigues teniendo problemas:
1. Captura una imagen del error
2. Indica qué método estás usando (IntelliJ/VS Code/Maven)
3. Comparte el mensaje de error completo

---

## 🎯 RESUMEN RÁPIDO

**La forma MÁS FÁCIL:**
1. Abre IntelliJ IDEA
2. File → Open → Selecciona `customer-service`
3. Espera a que sincronice
4. Click derecho en `CustomerServiceApplication.java` → Run
5. Configura los argumentos: `--spring.profiles.active=test --server.port=8098`
6. Ve a: `http://localhost:8098/clients`

**¡Eso es todo!** No necesitas Maven instalado para ejecutar el proyecto desde IntelliJ. 🚀

