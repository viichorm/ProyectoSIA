@echo off
cd C:\Proyecto\ProyectoSIA

:: Compilar el archivo MainProyecto.java
javac MainProyecto.java

:: Verificar si la compilación fue exitosa
if %errorlevel% neq 0 (
    echo Error en la compilación.
    pause
    exit /b
)

:: Ejecutar la clase MainProyecto
java MainProyecto

pause
