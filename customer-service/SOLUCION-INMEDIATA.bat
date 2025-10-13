@echo off
echo ========================================
echo   DIAGNOSTICO DE JAVA
echo ========================================
echo.

echo Verificando instalaciones de Java...
echo.

REM Verificar Java actual
echo [1] Java en PATH:
java -version 2>&1
echo.

REM Buscar otras instalaciones de Java en Program Files
echo [2] Buscando instalaciones de Java...
echo.

if exist "C:\Program Files\Java" (
    echo Encontrado en C:\Program Files\Java:
    dir "C:\Program Files\Java" /b 2>nul
    echo.
)

if exist "C:\Program Files (x86)\Java" (
    echo Encontrado en C:\Program Files (x86)\Java:
    dir "C:\Program Files (x86)\Java" /b 2>nul
    echo.
)

if exist "C:\Program Files\Eclipse Adoptium" (
    echo Encontrado en C:\Program Files\Eclipse Adoptium:
    dir "C:\Program Files\Eclipse Adoptium" /b 2>nul
    echo.
)

if exist "C:\Program Files\Amazon Corretto" (
    echo Encontrado en C:\Program Files\Amazon Corretto:
    dir "C:\Program Files\Amazon Corretto" /b 2>nul
    echo.
)

echo ========================================
echo   SOLUCION RECOMENDADA
echo ========================================
echo.
echo Tu sistema tiene Java 24, pero el proyecto necesita Java 17.
echo.
echo OPCION 1 - Descargar Java 17 (RECOMENDADO):
echo   1. Ve a: https://adoptium.net/es/temurin/releases/
echo   2. Selecciona: Version 17 (LTS), Windows, x64, JDK
echo   3. Descarga e instala
echo   4. Reinicia IntelliJ
echo   5. File -^> Project Structure -^> Project SDK: 17
echo.
echo OPCION 2 - Descargar desde IntelliJ:
echo   1. Abre IntelliJ IDEA
echo   2. File -^> Project Structure (Ctrl+Alt+Shift+S)
echo   3. Project SDK -^> Add SDK -^> Download JDK...
echo   4. Version: 17, Vendor: Eclipse Temurin
echo   5. Click Download
echo   6. Una vez descargado, seleccionalo como Project SDK
echo   7. Apply -^> OK
echo.
echo OPCION 3 - Usar IntelliJ sin Maven delegado:
echo   1. File -^> Settings (Ctrl+Alt+S)
echo   2. Build, Execution, Deployment -^> Build Tools -^> Maven -^> Runner
echo   3. DESMARCA: "Delegate IDE build/run actions to Maven"
echo   4. Apply -^> OK
echo   5. Build -^> Rebuild Project
echo.
echo ========================================
pause

