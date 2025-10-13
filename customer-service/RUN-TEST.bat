@echo off
echo ========================================
echo   CUSTOMER SERVICE - MODO PRUEBA
echo ========================================
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

