@echo off
REM ================================================================
REM Remove UTF-8 BOM from Solidity Files (Advanced - Windows)
REM ================================================================

setlocal enabledelayedexpansion

echo.
echo ================================================================
echo Removing UTF-8 BOM from Solidity files (Advanced Method)
echo Target: SOLMASFramework\contracts
echo ================================================================
echo.

cd /d "%~dp0SOLMASFramework\contracts" || (
    echo ERROR: SOLMASFramework\contracts directory not found
    pause
    exit /b 1
)

echo Current directory: %cd%
echo.

REM Use PowerShell with more aggressive BOM removal
powershell -NoProfile -Command ^
    "$files = Get-ChildItem -Path '.' -Filter '*.sol' -Recurse; " ^
    "$count = 0; " ^
    "$fixed = 0; " ^
    "foreach ($file in $files) { " ^
    "  $count++; " ^
    "  $relativePath = $file.FullName.Substring((Get-Location).Path.Length + 1); " ^
    "  Write-Host \"[$count] Processing: $relativePath\"; " ^
    "  try { " ^
    "    [byte[]]$bytes = [System.IO.File]::ReadAllBytes($file.FullName); " ^
    "    if ($bytes[0] -eq 0xEF -and $bytes[1] -eq 0xBB -and $bytes[2] -eq 0xBF) { " ^
    "      Write-Host '  Status: Removing BOM'; " ^
    "      $newBytes = $bytes[3..($bytes.Length-1)]; " ^
    "      [System.IO.File]::WriteAllBytes($file.FullName, $newBytes); " ^
    "      $fixed++; " ^
    "    } else { " ^
    "      Write-Host '  Status: No BOM'; " ^
    "    } " ^
    "  } catch { " ^
    "    Write-Host '  Error: ' $_.Exception.Message; " ^
    "  } " ^
    "} " ^
    "Write-Host \"\"; " ^
    "Write-Host \"Files processed: $count\"; " ^
    "Write-Host \"Files fixed: $fixed\"; "

cd /d "%~dp0"

echo.
echo ================================================================
echo Done
echo ================================================================
echo.

pause
exit /b 0
