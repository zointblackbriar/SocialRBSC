@echo off
REM ================================================================
REM Remove UTF-8 BOM from Solidity Files (Windows)
REM ================================================================
REM
REM This script removes UTF-8 BOM (Byte Order Mark) from all .sol files
REM in SOLMASFramework/contracts that were corrupted during version updates
REM
REM Usage: fix-utf8-bom.bat
REM
REM ================================================================

setlocal enabledelayedexpansion

echo.
echo ================================================================
echo Removing UTF-8 BOM from Solidity files
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

REM Use PowerShell to process all files recursively and remove BOM
powershell -NoProfile -Command ^
    "$files = Get-ChildItem -Path '.' -Filter '*.sol' -Recurse; " ^
    "$count = 0; " ^
    "$fixed = 0; " ^
    "foreach ($file in $files) { " ^
    "  $count++; " ^
    "  $relativePath = $file.FullName.Substring((Get-Location).Path.Length + 1); " ^
    "  Write-Host \"[$count] Processing: $relativePath\"; " ^
    "  $content = Get-Content $file.FullName -Encoding UTF8; " ^
    "  if ($content[0] -match '^\\xEF\\xBB\\xBF') { " ^
    "    Write-Host '  Status: Removing BOM'; " ^
    "    $content = $content -replace '^\\xEF\\xBB\\xBF', ''; " ^
    "    Set-Content $file.FullName $content -Encoding UTF8 -NoNewline; " ^
    "    $fixed++; " ^
    "  } else { " ^
    "    Write-Host '  Status: No BOM found'; " ^
    "  } " ^
    "} " ^
    "Write-Host \"\"; " ^
    "Write-Host \"Files processed: $count\"; " ^
    "Write-Host \"Files fixed: $fixed\"; "

REM Return to original directory
cd /d "%~dp0"

echo.
echo ================================================================
echo BOM Removal Complete
echo ================================================================
echo.
echo Next steps:
echo   1. Run: cd SOLMASFramework
echo   2. Run: npx hardhat compile
echo   3. Verify: No parser errors should appear
echo.

pause
exit /b 0
