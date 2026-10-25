@echo off
REM ================================================================
REM Re-run all measurements and validate the evaluation tables.
REM Equivalent to: node scripts\validate-tables.js --run
REM
REM Usage: run-verify-tables.bat [options]
REM   --strict                  treat warnings/skips as failures
REM   --module-report <path>    JSON for Table 6 module breakdown
REM   --table6-fixture <path>   deployment gas-report excerpt
REM   --report <path>           validation report JSON
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
echo Re-running measurements and validating evaluation tables...
node scripts\validate-tables.js --run %*
set "STATUS=%ERRORLEVEL%"

popd
if not "%STATUS%"=="0" (
    echo.
    echo ^! Verification FAILED with exit code %STATUS%.
    exit /b %STATUS%
)

echo.
echo SUCCESS: measurements and validation report regenerated.
exit /b 0
