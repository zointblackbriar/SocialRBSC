@echo off
REM ================================================================
REM SocialRbSC - Run the examples (Windows batch)
REM ================================================================
REM
REM Builds the framework, generates Solidity, runs unit/integration
REM tests, starts Ganache, and launches the example application.
REM
REM Usage: run-examples.bat [command]
REM Commands:
REM   setup        - Build parent framework and examples (default)
REM   build        - Build the whole project
REM   test         - Build and run unit tests (no Ganache required)
REM   integration  - Build and run integration tests (needs Ganache)
REM   run          - Build and launch the example application
REM   ganache      - Start a local Ganache node (deterministic accounts)
REM   solidity     - Compile SOLMASFramework Solidity contracts
REM   clean        - Remove all build artifacts
REM   help         - Show this help
REM ================================================================

setlocal enabledelayedexpansion

set SCRIPT_DIR=%~dp0
set ROOT_DIR=%SCRIPT_DIR%
set EXAMPLES_DIR=%SCRIPT_DIR%SocialRbSC-Examples
set SOLMAS_DIR=%SCRIPT_DIR%SOLMASFramework

set MAIN_CLASS=de.tudresden.socialrbscdeterministic.examples.compartments.ExamplesApp
set GANACHE_MNEMONIC=world reopen cute forward vintage okay drink margin piano buffalo autumn awful

set COMMAND=%1
if "%COMMAND%"=="" set COMMAND=help

if /i "%COMMAND%"=="help" goto help

echo.
echo ================================================================
echo SocialRbSC - Run Examples (Windows)
echo ================================================================
echo.

where mvn >nul 2>nul
if %errorlevel% neq 0 (
    echo ERROR: Maven is not installed or not in PATH.
    echo Install Apache Maven and add it to PATH, then retry.
    exit /b 1
)

if /i "%COMMAND%"=="setup" goto setup
if /i "%COMMAND%"=="build" goto build
if /i "%COMMAND%"=="test" goto test
if /i "%COMMAND%"=="integration" goto integration
if /i "%COMMAND%"=="run" goto run
if /i "%COMMAND%"=="ganache" goto ganache
if /i "%COMMAND%"=="solidity" goto solidity
if /i "%COMMAND%"=="clean" goto clean

echo ERROR: Unknown command: %COMMAND%
goto help

:setup
echo [1/1] Building parent framework and examples (skip tests)...
cd /d "%ROOT_DIR%"
call mvn clean install -DskipTests
if errorlevel 1 (
    echo ^✗ Setup failed
    exit /b 1
)
echo ^✓ Setup complete. Run: run-examples.bat test   or   run-examples.bat run
goto end

:build
echo [1/1] Building the full project (SocialRBSC-core + SocialRbSC-Examples)...
cd /d "%ROOT_DIR%"
call mvn clean install -DskipTests
if errorlevel 1 (
    echo ^✗ Build failed
    exit /b 1
)
echo ^✓ Build complete.
goto end

:test
echo [1/2] Building parent framework...
cd /d "%ROOT_DIR%"
call mvn clean install -DskipTests
if errorlevel 1 (
    echo ^✗ Parent build failed
    exit /b 1
)
echo [2/2] Running unit tests...
cd /d "%EXAMPLES_DIR%"
call mvn test
if errorlevel 1 (
    echo ^✗ Unit tests failed
    exit /b 1
)
echo ^✓ Unit tests complete.
goto end

:integration
echo [1/2] Building parent framework...
cd /d "%ROOT_DIR%"
call mvn clean install -DskipTests
if errorlevel 1 (
    echo ^✗ Parent build failed
    exit /b 1
)
echo [2/2] Running integration tests (requires Ganache on localhost:8545)...
cd /d "%EXAMPLES_DIR%"
call mvn verify
if errorlevel 1 (
    echo ^✗ Integration tests failed
    exit /b 1
)
echo ^✓ Integration tests complete.
goto end

:run
echo [1/2] Building parent framework...
cd /d "%ROOT_DIR%"
call mvn clean install -DskipTests
if errorlevel 1 (
    echo ^✗ Parent build failed
    exit /b 1
)
echo [2/2] Launching example application: %MAIN_CLASS%...
cd /d "%EXAMPLES_DIR%"
call mvn org.codehaus.mojo:exec-maven-plugin:3.1.0:java "-Dexec.mainClass=%MAIN_CLASS%"
if errorlevel 1 (
    echo ^✗ Application failed
    exit /b 1
)
goto end

:ganache
echo Starting Ganache with deterministic accounts...
echo Mnemonic: %GANACHE_MNEMONIC%
where ganache >nul 2>nul
if %errorlevel% equ 0 (
    call ganache -m "%GANACHE_MNEMONIC%"
) else (
    call npx ganache -m "%GANACHE_MNEMONIC%"
)
goto end

:solidity
echo Compiling SOLMASFramework Solidity contracts (Hardhat)...
cd /d "%SOLMAS_DIR%"
call npx hardhat compile
if errorlevel 1 (
    echo ^✗ Solidity compile failed
    exit /b 1
)
echo ^✓ Solidity compile complete.
goto end

:clean
echo [1/2] Cleaning Maven builds...
cd /d "%ROOT_DIR%"
call mvn clean
echo [2/2] Cleaning Solidity artifacts...
cd /d "%SOLMAS_DIR%"
call npx hardhat clean
echo ^✓ Clean complete.
goto end

:help
echo.
echo SocialRbSC - Run Examples
echo.
echo Usage: run-examples.bat [command]
echo.
echo Commands:
echo   setup        Build parent framework and examples (default full build)
echo   build        Build the whole project (SocialRBSC-core + SocialRbSC-Examples)
echo   test         Build and run unit tests (no Ganache required)
echo   integration  Build and run integration tests (requires Ganache on localhost:8545)
echo   run          Build and launch the example application
echo   ganache      Start a local Ganache node with the project's deterministic accounts
echo   solidity     Compile the SOLMASFramework Solidity contracts (Hardhat)
echo   clean        Remove all build artifacts
echo   help         Show this help
echo.
echo Typical workflow:
echo   1. run-examples.bat ganache      (in one terminal)
echo   2. run-examples.bat integration  (in another terminal)
echo   3. run-examples.bat run
echo.
goto end

:end
endlocal
exit /b 0
