@echo off
REM ================================================================
REM Run the full evaluation harness (baseline, lifecycle, scalability).
REM Must run through Hardhat because the script uses the Hardhat runtime.
REM
REM Usage: run-measure-evaluation.bat
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
echo Running evaluation harness...
call npx.cmd hardhat run scripts\measure-evaluation.js
set "STATUS=%ERRORLEVEL%"

popd
if not "%STATUS%"=="0" (
    echo.
    echo ^! Evaluation FAILED with exit code %STATUS%.
    exit /b %STATUS%
)

echo.
echo SUCCESS: evaluation harness completed.
exit /b 0
