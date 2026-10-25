@echo off
REM ================================================================
REM Regenerate the gas measurements used by the evaluation tables.
REM Writes scripts\results\measurements.json by default.
REM
REM Usage: run-measure-tables.bat [--out <path>]
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
echo Measuring gas tables...
node scripts\measure-tables.js %*
set "STATUS=%ERRORLEVEL%"

popd
if not "%STATUS%"=="0" (
    echo.
    echo ^! Measurement FAILED with exit code %STATUS%.
    exit /b %STATUS%
)

echo.
echo SUCCESS: measurement output written.
exit /b 0
