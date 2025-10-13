@echo off
echo ========================================
echo   INSTRUCCIONES PARA EJECUTAR EN INTELLIJ
echo ========================================
echo.
echo PASO 1: Abre IntelliJ IDEA
echo.
echo PASO 2: Abre el proyecto customer-service
echo         File -^> Open -^> Selecciona la carpeta customer-service
echo.
echo PASO 3: Espera a que IntelliJ descargue las dependencias
echo         (Verás una barra de progreso abajo a la derecha)
echo.
echo PASO 4: Navega a:
echo         src/main/java/com/content/customer_service/CustomerServiceApplication.java
echo.
echo PASO 5: Haz clic derecho en el archivo y selecciona:
echo         "Run CustomerServiceApplication"
echo.
echo PASO 6: Si ya se ejecutó una vez, configura los parámetros:
echo         - Click en "Edit Configurations" (arriba derecha)
echo         - En "Program arguments" agrega:
echo           --spring.profiles.active=test --server.port=8098
echo         - Click en "Apply" y luego "OK"
echo         - Click en el botón verde de "Run"
echo.
echo ========================================
echo   VERIFICACIÓN
echo ========================================
echo.
echo Una vez iniciado, abre tu navegador en:
echo   http://localhost:8098/clients
echo.
echo Para ver la consola de la base de datos H2:
echo   http://localhost:8098/h2-console
echo   - JDBC URL: jdbc:h2:mem:testdb
echo   - User: sa
echo   - Password: (dejar vacío)
echo.
echo ========================================
pause

