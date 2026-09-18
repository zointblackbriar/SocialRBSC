@echo off
REM ================================================================
REM Build and Install SocialRbSC Framework and Examples
REM For Windows Systems
REM ================================================================
REM
REM This script builds the parent SocialRbSC framework first,
REM then the Examples
REM
REM Usage: build-all.bat [command]
REM Commands:
REM   setup        - Build parent and examples (default)
REM   parent       - Build parent framework only
REM   examples     - Build examples only (requires parent built)
REM   clean        - Clean all builds
REM ================================================================

setlocal enabledelayedexpansion

echo.
echo ================================================================
echo SocialRbSC Framework and Examples - Build Script (Windows)
echo ================================================================
echo.

REM Check if Maven is installed
where mvn >nul 2>nul
if %errorlevel% neq 0 (
    echo ERROR: Maven is not installed or not in PATH
    exit /b 1
)

REM Get the script directory
set SCRIPT_DIR=%~dp0
set PARENT_DIR=%SCRIPT_DIR%
set EXAMPLES_DIR=%SCRIPT_DIR%SocialRbSC-Examples

REM Set command (default to setup)
set COMMAND=%1
if "%COMMAND%"=="" set COMMAND=setup

if /i "%COMMAND%"=="setup" (
    echo [1/2] Building parent SocialRbSC framework...
    cd /d "%PARENT_DIR%"
    call mvn clean install -DskipTests
    if !errorlevel! equ 0 (
        echo ^✓ Parent framework built successfully
        echo.
        echo [2/2] Building SocialRbSC Examples...
        cd /d "%EXAMPLES_DIR%"
        call mvn clean install
        if !errorlevel! equ 0 (
            echo ^✓ Examples built successfully
            echo.
            echo [3/3] Building Sphinx documentation...
            cd /d "%SCRIPT_DIR%Sphinx-documentation"
            if exist make.bat (
                call make.bat html
                if !errorlevel! neq 0 (
                    echo ^✗ Documentation build failed. You may need to install Sphinx.
                    exit /b 1
                ) else (
                    echo ^✓ Documentation built in Sphinx-documentation\build\html
                )
            ) else (
                echo Note: make.bat not found; skipping documentation step
            )
            echo.
            echo ================================================================
            echo Build Complete!
            echo ================================================================
            echo.
            echo To run the application:
            echo   cd SocialRbSC-Examples
            echo   mvn exec:java -Dexec.mainClass="de.tudresden.socialrbscdeterministic.examples.compartments.ExamplesApp"
            echo.
        ) else (
            echo ^✗ Examples build failed
            exit /b 1
        )
    ) else (
        echo ^✗ Parent framework build failed
        exit /b 1
    )
) else if /i "%COMMAND%"=="parent" (
    echo Building parent SocialRbSC framework...
    cd /d "%PARENT_DIR%"
    call mvn clean install -DskipTests
    if !errorlevel! equ 0 (
        echo ^✓ Parent framework built successfully
    ) else (
        echo ^✗ Parent framework build failed
        exit /b 1
    )
) else if /i "%COMMAND%"=="examples" (
    echo Building SocialRbSC Examples...
    cd /d "%EXAMPLES_DIR%"
    call mvn clean install
    if !errorlevel! equ 0 (
        echo ^✓ Examples built successfully
    ) else (
        echo ^✗ Examples build failed
        exit /b 1
    )
) else if /i "%COMMAND%"=="clean" (
    echo Cleaning all builds...
    cd /d "%PARENT_DIR%"
    call mvn clean
    cd /d "%EXAMPLES_DIR%"
    call mvn clean
    echo ^✓ Clean completed
) else (
    echo ERROR: Unknown command: %COMMAND%
    echo.
    echo Valid commands:
    echo   setup        - Build parent and examples (default)
    echo   parent       - Build parent framework only
    echo   examples     - Build examples only
    echo   clean        - Clean all builds
    exit /b 1
)

endlocal
exit /b 0
