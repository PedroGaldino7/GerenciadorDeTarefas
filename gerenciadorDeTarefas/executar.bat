@echo off
chcp 65001 >nul
javac -encoding UTF-8 -d bin src/*.java
if %errorlevel% neq 0 (
    echo.
    echo ❌ Erro ao compilar o código!
    pause
    exit /b
)
java -cp bin Main
pause
