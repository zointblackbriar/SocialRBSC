@echo off
REM ================================================================
REM Compile the Solidity contracts referenced by the paper's appendix
REM (ConcreteMediator, MedicalSupplyMission, DeliveryProviderRole)
REM and fail on any compilation error.
REM ================================================================
setlocal

set ROOT=%~dp0

echo.
echo Compiling SOLMASFramework contracts (including paper snippets)...
cd /d "%ROOT%"
call npx.cmd hardhat compile
if errorlevel 1 (
    echo.
    echo ^! Compilation FAILED with errors. See output above.
    exit /b 1
)

echo.
echo Verifying paper snippet artifacts...
if not exist "artifacts\contracts\societypattern\ConcreteMediator.sol\ConcreteMediator.json" (
    echo ^! Missing artifact: societypattern\ConcreteMediator.sol
    exit /b 1
)
if not exist "artifacts\contracts\paper_snippets\MedicalSupplyMission.sol\MedicalSupplyMission.json" (
    echo ^! Missing artifact: paper_snippets\MedicalSupplyMission.sol
    exit /b 1
)
if not exist "artifacts\contracts\paper_snippets\DeliveryProviderRole.sol\DeliveryProviderRole.json" (
    echo ^! Missing artifact: paper_snippets\DeliveryProviderRole.sol
    exit /b 1
)

echo.
echo SUCCESS: all paper snippet contracts compiled without error.
echo   - societypattern\ConcreteMediator.sol
echo   - paper_snippets\MedicalSupplyMission.sol
echo   - paper_snippets\DeliveryProviderRole.sol
exit /b 0
