@echo off
REM ================================================================
REM Full Test and Verify Script for Windows
REM ================================================================
REM
REM This script:
REM 1. Checks if Docker Desktop is running
REM 2. Starts Ganache via Docker if available
REM 3. Runs unit tests (mvn test) on all projects
REM 4. Runs integration tests (mvn verify) on all projects
REM
REM Usage: test-all.bat [skip-docker] [test-type]
REM   skip-docker - Skip Docker and Ganache setup (optional)
REM   test-type   - Specify which tests to run: unit, integration, or all (optional)
REM              Default: all
REM
REM Examples:
REM   test-all.bat                     - Full test with Docker (slowest)
REM   test-all.bat skip-docker         - Skip Docker, run all tests (faster)
REM   test-all.bat skip-docker unit    - Skip Docker, unit tests only (fastest)
REM
REM Notes:
REM   - Unit tests are optimized to skip web3j code generation
REM   - Parallel test execution is enabled (-T 1C)
REM   - Maven logging is reduced for faster output
REM ================================================================

setlocal enabledelayedexpansion

echo.
echo ================================================================
echo Full Maven Test and Verify Script (Windows)
echo ================================================================
echo.

REM Get command line parameters
set SKIP_DOCKER=%1
set TEST_TYPE=%2
if "%TEST_TYPE%"=="" set TEST_TYPE=all
echo Test Type: %TEST_TYPE% (unit/integration/all)
echo.

REM Check for skip-docker flag
set SKIP_DOCKER=%1
if "%SKIP_DOCKER%"=="skip-docker" (
    echo Docker check skipped by user
    goto :SKIP_DOCKER_CHECK
)

REM Check if Docker is installed
where docker >nul 2>nul
if %errorlevel% neq 0 (
    echo [WARNING] Docker is not installed or not in PATH
    echo [WARNING] Skipping Docker and Ganache setup
    echo.
    goto :SKIP_DOCKER_CHECK
)

REM Check if Docker Desktop is running (Windows)
echo Checking Docker Desktop status...
docker info >nul 2>nul
if %errorlevel% neq 0 (
    echo [WARNING] Docker Desktop is not running or Docker daemon is not accessible
    echo [INFO] Please start Docker Desktop manually or use 'test-all.bat skip-docker' to skip
    echo.
    pause
    goto :SKIP_DOCKER_CHECK
)

echo ✓ Docker is running
echo.

REM Check if ganache-docker.bat exists
if exist "ganache-docker.bat" (
    echo Starting Ganache via Docker...
    echo.
    call ganache-docker.bat
    echo.
    echo ✓ Ganache started
    echo.
    timeout /t 3 /nobreak
) else (
    echo [WARNING] ganache-docker.bat not found in current directory
    echo [WARNING] Skipping Ganache startup
    echo.
)

:SKIP_DOCKER_CHECK

echo ================================================================
echo Starting Maven Tests and Verification
echo ================================================================
echo.

REM Check if Maven is installed
where mvn >nul 2>nul
if %errorlevel% neq 0 (
    echo ERROR: Maven is not installed or not in PATH
    echo Please install Apache Maven: https://maven.apache.org/download.cgi
    exit /b 1
)

echo Detected Maven version:
call mvn --version
echo.

REM Get list of directories with pom.xml files
set TEST_COUNT=0
for /r . %%I in (pom.xml) do (
    REM ignore directories named pom.xml (node_modules etc)
    if exist "%%~fI\" (
        rem %%I is a directory, skip
    ) else (
        set "PROJECT_DIR=%%~dpI"
        REM skip SOLMASFramework (not a Maven module)
        echo !PROJECT_DIR! | findstr /i "SOLMASFramework" >nul
        if !errorlevel! equ 0 (
            REM skip SOLMASFramework
            ) else (
            REM skip generated smart contract output directories
            echo !PROJECT_DIR! | findstr /i "generatedsmartcontract" >nul
            if !errorlevel! equ 0 (
                REM skip generatedsmartcontract
            ) else (
            REM skip files in hidden directories (names starting with a dot)
            echo !PROJECT_DIR! | findstr /i "\\\.">nul
            if !errorlevel! neq 0 (
                REM Only count directories that actually contain a pom.xml file
                if exist "!PROJECT_DIR!pom.xml" (
                    set /a TEST_COUNT+=1
                ) else (
                    echo Skipping !PROJECT_DIR! (no pom.xml)
                )
            )
        )
    )
)

if %TEST_COUNT% equ 0 (
    echo ERROR: No pom.xml files found in project
    exit /b 1
)

echo Found %TEST_COUNT% Maven project(s)
echo.

REM Store current directory
set ROOT_DIR=%cd%

REM Counter for tracking projects
set PROJECT_NUM=0

REM Phase 1: Run unit tests
if "%TEST_TYPE%"=="all" goto :RUN_UNIT_TESTS
if "%TEST_TYPE%"=="unit" goto :RUN_UNIT_TESTS
goto :SKIP_UNIT_TESTS

:RUN_UNIT_TESTS
echo ================================================================
echo [Phase 1/2] Running mvn clean test (Unit Tests) on all projects
echo ================================================================
echo.

for /r . %%I in (pom.xml) do (
    REM skip directories named pom.xml
    if exist "%%~fI\" (
        rem directory, ignore
    ) else (
        set "PROJECT_DIR=%%~dpI"
        
        REM skip SOLMASFramework (not a Maven module)
        echo !PROJECT_DIR! | findstr /i "SOLMASFramework" >nul
        if !errorlevel! equ 0 (
            REM Skipping SOLMASFramework - not a Maven module
        ) else (
            REM skip generated smart contract output directories
            echo !PROJECT_DIR! | findstr /i "generatedsmartcontract" >nul
            if !errorlevel! equ 0 (
                REM skipping generatedsmartcontract
            ) else (
            REM skip any pom inside .git or other VCS directories
            echo !PROJECT_DIR! | findstr /i "\\\." >nul
            if !errorlevel! equ 0 (
                REM ignoring project under hidden directory
                rem echo Skipping !PROJECT_DIR! (hidden)
            ) else (
                set /a PROJECT_NUM+=1
                REM Only run unit tests if a pom.xml exists in the project directory
                if exist "!PROJECT_DIR!pom.xml" (
                    echo [%PROJECT_NUM%/%TEST_COUNT%] Unit Tests: !PROJECT_DIR!
                    cd /d "!PROJECT_DIR!"

                    if !errorlevel! equ 0 (
                        REM Run tests with optimizations: skip web3j codegen, parallel execution, reduced logging
                        call mvn clean test -T 1C -Dorg.slf4j.simpleLogger.defaultLogLevel=warn -Dskip.solidity.codegen=true
                        if !errorlevel! neq 0 (
                            echo [ERROR] Unit tests failed in !PROJECT_DIR!
                            cd /d "%ROOT_DIR%"
                            echo.
                            echo ================================================================
                            echo Unit Tests FAILED
                            echo ================================================================
                            exit /b 1
                        )
                    )
                ) else (
                    echo Skipping !PROJECT_DIR! (no pom.xml)
                )
            )
        )
    )
)

cd /d "%ROOT_DIR%"
echo.

:SKIP_UNIT_TESTS

REM Phase 2: Run integration tests
if "%TEST_TYPE%"=="all" goto :RUN_INTEGRATION_TESTS
if "%TEST_TYPE%"=="integration" goto :RUN_INTEGRATION_TESTS
goto :SKIP_INTEGRATION_TESTS

:RUN_INTEGRATION_TESTS
echo ================================================================
echo [Phase 2/2] Running mvn verify (Integration Tests) on all projects
echo ================================================================
echo.

set PROJECT_NUM=0

for /r . %%I in (pom.xml) do (
    REM skip directories named pom.xml
    if exist "%%~fI\" (
        rem directory, ignore
    ) else (
        set "PROJECT_DIR=%%~dpI"
        
        REM skip SOLMASFramework (not a Maven module)
        echo !PROJECT_DIR! | findstr /i "SOLMASFramework" >nul
        if !errorlevel! equ 0 (
            REM Skipping SOLMASFramework - not a Maven module
        ) else (
            REM skip generated smart contract output directories
            echo !PROJECT_DIR! | findstr /i "generatedsmartcontract" >nul
            if !errorlevel! equ 0 (
                REM skipping generatedsmartcontract
            ) else (
            REM skip any pom inside .git or other VCS directories
            echo !PROJECT_DIR! | findstr /i "\\\." >nul
            if !errorlevel! equ 0 (
                rem echo Skipping !PROJECT_DIR! (hidden)
            ) else (
                set /a PROJECT_NUM+=1
                REM Only run integration tests if a pom.xml exists in the project directory
                if exist "!PROJECT_DIR!pom.xml" (
                    echo [%PROJECT_NUM%/%TEST_COUNT%] Integration Tests: !PROJECT_DIR!
                    cd /d "!PROJECT_DIR!"

                    if !errorlevel! equ 0 (
                        REM Run integration tests with optimizations: skip web3j codegen, parallel execution, reduced logging
                        call mvn verify -T 1C -Dorg.slf4j.simpleLogger.defaultLogLevel=warn -Dskip.solidity.codegen=true
                        if !errorlevel! neq 0 (
                            echo [ERROR] Integration tests failed in !PROJECT_DIR!
                            cd /d "%ROOT_DIR%"
                            echo.
                            echo ================================================================
                            echo Integration Tests FAILED
                            echo ================================================================
                            exit /b 1
                        )
                    )
                ) else (
                    echo Skipping !PROJECT_DIR! (no pom.xml)
                )
            )
        )
    )
)

:SKIP_INTEGRATION_TESTS

cd /d "%ROOT_DIR%"

echo.
echo ================================================================
echo ✓ All tests and verification completed successfully!
echo ================================================================
echo.

endlocal
exit /b 0
