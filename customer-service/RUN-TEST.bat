@echo off
echo ========================================
echo   CUSTOMER SERVICE - MODO PRUEBA
echo ========================================
echo.

REM Verificar si Maven está instalado
where mvn >nul 2>nul
if %ERRORLEVEL% NEQ 0 (
    echo ⚠️  MAVEN NO DETECTADO ⚠️
    echo.
    echo Maven no está instalado o no está en el PATH.
    echo.
    echo ========================================
    echo   OPCIONES DISPONIBLES:
    echo ========================================
    echo.
    echo OPCIÓN 1 - USAR INTELLIJ IDEA (RECOMENDADO):
    echo   1. Abre IntelliJ IDEA
    echo   2. File -^> Open -^> Selecciona la carpeta customer-service
    echo   3. Espera a que descargue dependencias (barra abajo derecha^)
    echo   4. Ve a: src/main/java/com/.../CustomerServiceApplication.java
    echo   5. Click derecho -^> Run 'CustomerServiceApplication'
    echo   6. Agrega argumentos: --spring.profiles.active=test --server.port=8098
    echo   7. Abre: http://localhost:8098/clients
    echo.
    echo OPCIÓN 2 - INSTALAR MAVEN:
    echo   Ejecuta: RUN-INTELLIJ.bat para ver instrucciones
    echo.
    echo OPCIÓN 3 - LEER GUÍA COMPLETA:
    echo   Abre: EJECUTAR-SIN-MAVEN.md
    echo.
    echo ========================================
    echo.
    pause
    exit /b 1
)

echo ✅ Maven detectado - Continuando...
echo.
echo Configuracion:
echo - Base de datos: H2 (en memoria)
echo - Puerto: 8098
echo - Datos de prueba: SI
echo - Consola H2: http://localhost:8098/h2-console
echo.
echo ========================================

cd /d "%~dp0"

echo Compilando proyecto...
call mvn clean compile

if %ERRORLEVEL% NEQ 0 (
    echo.
    echo ERROR: La compilacion fallo
    pause
    exit /b 1
)

echo.
echo Iniciando aplicacion...
echo.
call mvn spring-boot:run -Dspring-boot.run.arguments="--spring.profiles.active=test --server.port=8098"

pause
