@echo off
:: Guardar la ruta del directorio actual donde se encuentra el .bat
set DIR=%~dp0

:: Cambiar al directorio del proyecto
cd /d "%DIR%"

:: Compilar el archivo MainProyecto.java
javac Controlador\MainProyecto.java Controlador\CargarArchivo.java Modelo\*.java Vistaa\*.java -d bin

:: Verificar si la compilación fue exitosa
if %errorlevel% neq 0 (
    echo Error en la compilación.
    pause
    exit /b
)

:: Ejecutar la clase MainProyecto
java -cp bin Controlador.MainProyecto
pause
