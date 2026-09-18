@echo off
REM ================================================================
REM Fast Unit Test Script for Windows
REM ================================================================
REM
REM This script runs ONLY unit tests with maximum optimizations:
REM - Skips Docker and Ganache
REM - Skips integration tests
REM - Disables unnecessary plugins
REM - Runs tests in parallel
REM - Reduces logging verbosity
REM
REM This is the fastest way to run unit tests during development.
REM
REM Usage: test-fast.bat
REM ================================================================

setlocal enabledelayedexpansion

echo.
echo ================================================================
echo Fast Unit Test Script (Windows) - Optimized for Speed
echo ================================================================
echo.

REM Check if Maven is installed
where mvn >nul 2>nul
if %errorlevel% neq 0 (
    echo ERROR: Maven is not installed or not in PATH
    echo Please install Apache Maven: https://maven.apache.org/download.cgi
    exit /b 1
)

echo Maven version:
call mvn --version
echo.

REM Store current directory
set ROOT_DIR=%cd%

echo ================================================================
echo Running Fast Unit Tests (skip-docker, unit tests only)
echo ================================================================
echo.

REM Run Maven with optimizations
echo [*] Compiling and running unit tests...
echo [*] Optimizations: skip web3j, skip ITs, parallel build, reduced logging
echo.

cd /d "%ROOT_DIR%"
call mvn clean test ^
    -T 1C ^
    -DskipITs=true ^
    -Dorg.slf4j.simpleLogger.defaultLogLevel=warn ^
    -Dorg.slf4j.simpleLogger.showDateTime=false ^
    -Dmaven.compile.fork=true ^
    -f pom.xml

if !errorlevel! neq 0 (
    echo.
    echo ================================================================
    echo Unit Tests FAILED
    echo ================================================================
    exit /b 1
) else (
    echo.
echo ================================================================
echo ✓ All unit tests completed successfully!
echo ================================================================
echo.
)

endlocal
exit /b 0
