#!/usr/bin/env pwsh
<#
.SYNOPSIS
    Run the SocialRbSC examples: build the framework, generate Solidity,
    run unit/integration tests, start Ganache, and launch the example app.

.DESCRIPTION
    This script wraps the full SocialRbSC workflow. It must be run from the
    project root (socialagentrolebasedsmartcontract/) or anywhere (it locates
    its own directory).

.PARAMETER Command
    One of: setup, build, test, integration, run, ganache, solidity, clean, help.

.EXAMPLE
    .\run-examples.ps1 setup
    .\run-examples.ps1 test
    .\run-examples.ps1 run
#>

param(
    [Parameter(Position = 0)]
    [ValidateSet("setup", "build", "test", "integration", "run", "ganache", "solidity", "clean", "help")]
    [string]$Command = "help"
)

$ErrorActionPreference = "Stop"

$ScriptDir   = Split-Path -Parent $MyInvocation.MyCommand.Path
$RootDir     = $ScriptDir
$ExamplesDir = Join-Path $RootDir "SocialRbSC-Examples"
$SolmasDir   = Join-Path $RootDir "SOLMASFramework"

$MainClass   = "de.tudresden.socialrbscdeterministic.examples.compartments.ExamplesApp"
$GanacheMnemonic = "world reopen cute forward vintage okay drink margin piano buffalo autumn awful"

function Write-Step([string]$Msg) {
    Write-Host ""
    Write-Host ("==> " + $Msg) -ForegroundColor Cyan
}

function Invoke-Mvn([string]$Args, [string]$WorkDir) {
    Push-Location $WorkDir
    try {
        Write-Host ("[mvn] " + $Args)
        & mvn $Args
        if ($LASTEXITCODE -ne 0) { throw "Maven command failed (exit $LASTEXITCODE): mvn $Args" }
    }
    finally {
        Pop-Location
    }
}

function Show-Help {
    @"
SocialRbSC - run the examples (PowerShell)

Usage: .\run-examples.ps1 [command]

Commands:
  setup        Build the parent framework and examples (default full build)
  build        Build the whole project (SocialRBSC-core + SocialRbSC-Examples)
  test         Build and run unit tests (no Ganache required)
  integration  Build and run integration tests (requires Ganache on localhost:8545)
  run          Build and launch the example application
  ganache      Start a local Ganache node with the project's deterministic accounts
  solidity     Compile the SOLMASFramework Solidity contracts (Hardhat)
  clean        Remove all build artifacts
  help         Show this help

Examples:
  .\run-examples.ps1 setup
  .\run-examples.ps1 ganache      # in one terminal
  .\run-examples.ps1 integration  # in another terminal
  .\run-examples.ps1 run
"@
}

switch ($Command) {
    "help" {
        Show-Help
    }

    "setup" {
        Write-Step "Building parent framework and examples (skip tests)"
        Invoke-Mvn "clean install -DskipTests" $RootDir
        Write-Step "Setup complete. Run '.\\run-examples.ps1 run' or '.\\run-examples.ps1 test'."
    }

    "build" {
        Write-Step "Building the full project (SocialRBSC-core + SocialRbSC-Examples)"
        Invoke-Mvn "clean install -DskipTests" $RootDir
        Write-Step "Build complete."
    }

    "test" {
        Write-Step "Building parent framework"
        Invoke-Mvn "clean install -DskipTests" $RootDir
        Write-Step "Running unit tests"
        Invoke-Mvn "test" $ExamplesDir
        Write-Step "Unit tests complete."
    }

    "integration" {
        Write-Step "Building parent framework"
        Invoke-Mvn "clean install -DskipTests" $RootDir
        Write-Step "Running integration tests (requires Ganache on localhost:8545)"
        Invoke-Mvn "verify" $ExamplesDir
        Write-Step "Integration tests complete."
    }

    "run" {
        Write-Step "Building parent framework"
        Invoke-Mvn "clean install -DskipTests" $RootDir
        Write-Step "Launching example application: $MainClass"
        Push-Location $ExamplesDir
        try {
            & mvn "org.codehaus.mojo:exec-maven-plugin:3.1.0:java" "-Dexec.mainClass=$MainClass"
            if ($LASTEXITCODE -ne 0) { throw "exec:java failed (exit $LASTEXITCODE)" }
        }
        finally {
            Pop-Location
        }
    }

    "ganache" {
        Write-Step "Starting Ganache with deterministic accounts"
        Write-Host "Mnemonic: $GanacheMnemonic"
        $ganache = Get-Command ganache -ErrorAction SilentlyContinue
        if ($ganache) {
            & ganache -m $GanacheMnemonic
        }
        else {
            & npx ganache -m $GanacheMnemonic
        }
    }

    "solidity" {
        Write-Step "Compiling SOLMASFramework Solidity contracts (Hardhat)"
        Push-Location $SolmasDir
        try {
            & npx hardhat compile
            if ($LASTEXITCODE -ne 0) { throw "hardhat compile failed (exit $LASTEXITCODE)" }
        }
        finally {
            Pop-Location
        }
        Write-Step "Solidity compile complete."
    }

    "clean" {
        Write-Step "Cleaning Maven builds"
        Invoke-Mvn "clean" $RootDir
        Write-Step "Cleaning Solidity artifacts"
        Push-Location $SolmasDir
        try {
            & npx hardhat clean
        }
        finally {
            Pop-Location
        }
        Write-Step "Clean complete."
    }
}
