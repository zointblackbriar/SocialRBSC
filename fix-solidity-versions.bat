@echo off
REM ================================================================
REM Solidity Version Standardization Script (Windows - All Folders)
REM ================================================================
REM
REM This script updates all Solidity pragma statements in 
REM SOLMASFramework/contracts and ALL subfolders to use version 0.8.13
REM
REM Usage: fix-solidity-versions.bat
REM
REM ================================================================

setlocal enabledelayedexpansion

echo.
echo ================================================================
echo Standardizing Solidity versions to 0.8.13
echo Target: SOLMASFramework\contracts and ALL subfolders
echo ================================================================
echo.

REM Navigate to SOLMASFramework/contracts
cd /d "%~dp0SOLMASFramework\contracts" || (
    echo ERROR: SOLMASFramework\contracts directory not found
    pause
    exit /b 1
)

echo Current directory: %cd%
echo.

REM Use PowerShell to process all files recursively
powershell -NoProfile -Command ^
    "$files = Get-ChildItem -Path '.' -Filter '*.sol' -Recurse; " ^
    "$count = 0; " ^
    "$updated = 0; " ^
    "foreach ($file in $files) { " ^
    "  $count++; " ^
    "  $relativePath = $file.FullName.Substring((Get-Location).Path.Length + 1); " ^
    "  Write-Host \"[$count] Processing: $relativePath\"; " ^
    "  $content = Get-Content $file.FullName -Raw; " ^
    "  $original = $content; " ^
    "  $content = $content -replace 'pragma solidity [^;]*;', 'pragma solidity 0.8.13;'; " ^
    "  if ($content -ne $original) { " ^
    "    Set-Content $file.FullName $content -Encoding UTF8; " ^
    "    $updated++; " ^
    "    Write-Host '  Status: UPDATED'; " ^
    "  } else { " ^
    "    Write-Host '  Status: Already correct'; " ^
    "  } " ^
    "} " ^
    "Write-Host \"\"; " ^
    "Write-Host \"Files processed: $count\"; " ^
    "Write-Host \"Files updated: $updated\"; "

REM Return to original directory
cd /d "%~dp0"

echo.
echo ================================================================
echo Standardization Complete
echo ================================================================
echo.
echo Verify changes with:
echo   findstr /S /R "pragma solidity" SOLMASFramework\contracts\*.sol
echo.
echo Next steps:
echo   1. Run: mvn clean compile
echo   2. Run: mvn generate-sources
echo   3. Restart Ganache: .\ganache-docker.bat
echo   4. Rerun tests: .\test-all.bat skip-docker unit
echo.

pause
exit /b 0

