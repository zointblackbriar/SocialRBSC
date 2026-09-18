@echo off
REM ================================================================
REM SocialRbSC Examples - Maven Build and Run Script for Windows
REM ================================================================
REM
REM This script builds and runs the SocialRbSC Examples project
REM on Windows systems using Apache Maven.
REM
REM Usage: run-app.bat [command]
REM Commands:
REM   build        - Build the project only
REM   test         - Build and run tests only
REM   run          - Build and run the application
REM   clean        - Clean build artifacts
REM   rebuild      - Clean and rebuild the project
REM   (default)    - Full build, test, and run
REM ================================================================

setlocal enabledelayedexpansion

echo.
echo ================================================================
echo SocialRbSC Examples - Maven Application Runner (Windows)
echo ================================================================
echo.

REM Check if Maven is installed
where mvn >nul 2>nul
if %errorlevel% neq 0 (
    echo ERROR: Maven is not installed or not in PATH
    echo Please install Apache Maven and add it to your system PATH
    echo Visit: https://maven.apache.org/download.cgi
    exit /b 1
)

echo Detected Maven version:
call mvn --version
echo.

REM Get the script directory
set SCRIPT_DIR=%~dp0
cd /d "%SCRIPT_DIR%"

REM Set command (default to full build and run)
set COMMAND=%1
if "%COMMAND%"=="" set COMMAND=run

echo Project Directory: %cd%
echo.

REM Execute command
if /i "%COMMAND%"=="clean" (
    echo [1/1] Cleaning build artifacts...
    call mvn clean
    if !errorlevel! equ 0 (
        echo.
        echo ✓ Clean completed successfully
    ) else (
        echo.
        echo ✗ Clean failed with error code !errorlevel!
        exit /b !errorlevel!
    )
) else if /i "%COMMAND%"=="build" (
    echo [1/1] Building project...
    call mvn clean compile
    if !errorlevel! equ 0 (
        echo.
        echo ✓ Build completed successfully
    ) else (
        echo.
        echo ✗ Build failed with error code !errorlevel!
        exit /b !errorlevel!
    )
) else if /i "%COMMAND%"=="test" (
    echo [1/2] Building project...
    call mvn clean compile
    if !errorlevel! neq 0 (
        echo.
        echo ✗ Build failed with error code !errorlevel!
        exit /b !errorlevel!
    )
    echo ✓ Build completed
    echo.
    echo [2/2] Running tests...
    call mvn test
    if !errorlevel! equ 0 (
        echo.
        echo ✓ Tests completed successfully
    ) else (
        echo.
        echo ✗ Tests failed with error code !errorlevel!
        exit /b !errorlevel!
    )
) else if /i "%COMMAND%"=="rebuild" (
    echo [1/1] Cleaning and rebuilding project...
    call mvn clean install
    if !errorlevel! equ 0 (
        echo.
        echo ✓ Rebuild completed successfully
    ) else (
        echo.
        echo ✗ Rebuild failed with error code !errorlevel!
        exit /b !errorlevel!
    )
) else if /i "%COMMAND%"=="run" (
    echo [1/3] Cleaning previous builds...
    call mvn clean
    if !errorlevel! neq 0 (
        echo.
        echo ✗ Clean failed with error code !errorlevel!
        exit /b !errorlevel!
    )
    echo ✓ Clean completed
    echo.
    echo [2/3] Building project...
    call mvn compile
    if !errorlevel! neq 0 (
        echo.
        echo ✗ Build failed with error code !errorlevel!
        exit /b !errorlevel!
    )
    echo ✓ Build completed
    echo.
    echo [3/3] Running tests...
    call mvn test
    if !errorlevel! equ 0 (
        echo.
        echo ✓ Tests completed successfully
        echo.
        echo ================================================================
        echo Application Ready!
        echo ================================================================
        echo.
        echo To run the main application:
        echo   mvn exec:java -Dexec.mainClass="de.tudresden.socialrbscdeterministic.examples.compartments.ExamplesApp"
        echo.
    ) else (
        echo.
        echo ✗ Tests failed with error code !errorlevel!
        echo Application will not run due to test failures
        exit /b !errorlevel!
    )
) else (
    echo ERROR: Unknown command: %COMMAND%
    echo.
    echo Valid commands:
    echo   build        - Build the project only
    echo   test         - Build and run tests only
    echo   run          - Build and run the application (default)
    echo   clean        - Clean build artifacts
    echo   rebuild      - Clean and rebuild the project
    exit /b 1
)

echo.
echo ================================================================
echo Script completed successfully!
echo ================================================================

endlocal
exit /b 0
