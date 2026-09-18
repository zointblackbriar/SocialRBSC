@echo off
echo    Ganache Docker Setup Script

REM Simple and robust: avoid complex FOR blocks that can break in some shells.
set "CONTAINER_NAME=ganache-container"

REM Docker availability
docker version >nul 2>&1
if errorlevel 1 (
  echo Docker does not appear to be available. Please install Docker or ensure the Docker daemon is running.
  exit /b 1
)

REM Check if container is already running
echo Checking if container "%CONTAINER_NAME%" is already running...
for /f "tokens=*" %%i in ('docker ps --filter name^=%CONTAINER_NAME% --format "{{.Names}}" 2^>nul') do (
  if "%%i"=="%CONTAINER_NAME%" (
    echo Container is already running.
    goto :show_info
  )
)

REM Try to start an existing container
echo Attempting to start existing container "%CONTAINER_NAME%"...
docker start %CONTAINER_NAME% >nul 2>&1
if not errorlevel 1 (
  echo Container started successfully.
  goto :show_info
)

REM If we get here, container doesn't exist or can't be started
echo Could not start container (it may not exist or is in a bad state). Removing any conflicting container and creating a new one...
docker rm -f %CONTAINER_NAME% >nul 2>&1

echo Pulling latest Ganache image...
docker pull trufflesuite/ganache:latest
if errorlevel 1 (
  echo Failed to pull Ganache image. Check your network connection.
  exit /b 1
)

echo Starting Ganache with your mnemonic...
docker run -d --name %CONTAINER_NAME% -p 8545:8545 trufflesuite/ganache:latest ^
  --wallet.mnemonic "world reopen cute forward vintage okay drink margin piano buffalo autumn awful" ^
  --wallet.totalAccounts 10 ^
  --wallet.defaultBalance 1000 ^
  --miner.blockGasLimit 30000000 ^
  --miner.defaultGasPrice 2000000000 ^
  --miner.callGasLimit 50000000 ^
  --chain.hardfork shanghai ^
  --chain.networkId 1337 ^
  --chain.chainId 1337 >nul

if errorlevel 1 (
  echo Failed to start Ganache container.
  exit /b 1
)

:show_info
echo.
echo ========================================
echo    Ganache is running!
echo ========================================
echo RPC URL: http://localhost:8545
echo Network ID: 1337
echo Chain ID: 1337
echo Hardfork: shanghai
echo Default Gas Price: 2 Gwei (2000000000 wei)
echo Block Gas Limit: 30000000
echo Call Gas Limit: 50000000
echo.
echo To view logs: docker logs -f %CONTAINER_NAME%
echo To stop: docker stop %CONTAINER_NAME%
echo To remove: docker rm %CONTAINER_NAME%
echo ========================================

REM Show container status
timeout /t 2 /nobreak >nul 2>&1
docker ps --filter name=%CONTAINER_NAME%

REM Show recent logs
echo.
echo Recent logs:
docker logs --tail 20 %CONTAINER_NAME%