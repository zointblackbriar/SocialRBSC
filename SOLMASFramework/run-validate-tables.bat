@echo off
REM ================================================================
REM Validate the evaluation tables against the cached measurements.
REM
REM Usage: run-validate-tables.bat [options]
REM   --run                     re-run measurements instead of the cache
REM   --measurements <path>     measurement JSON
REM   --module-report <path>    JSON for Table 6 module breakdown
REM   --table6-fixture <path>   deployment gas-report excerpt
REM   --report <path>           validation report JSON
REM   --strict                  treat warnings/skips as failures
REM ================================================================
setlocal
set "ROOT=%~dp0"
pushd "%ROOT%" || exit /b 1

if not exist "node_modules" (
    echo ^! node_modules not found. Run "npm install" first.
    popd
    exit /b 1
)

echo.
echo Validating evaluation tables...
node scripts\validate-tables.js %*
set "STATUS=%ERRORLEVEL%"

popd
if not "%STATUS%"=="0" (
    echo.
    echo ^! Validation FAILED with exit code %STATUS%.
    exit /b %STATUS%
)

echo.
echo SUCCESS: validation report written.
exit /b 0
