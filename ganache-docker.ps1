Write-Host "========================================" -ForegroundColor Green
Write-Host "   Ganache Docker Setup Script" -ForegroundColor Green
Write-Host "========================================" -ForegroundColor Green

$CONTAINER_NAME = "ganache-container"

# Check if container is already running
Write-Host "Checking if container '$CONTAINER_NAME' is already running..." -ForegroundColor Yellow
$running = docker ps --filter "name=$CONTAINER_NAME" --format "{{.Names}}" 2>$null
if ($running -eq $CONTAINER_NAME) {
    Write-Host "Container is already running." -ForegroundColor Green
} else {
    # Try to start an existing container
    Write-Host "Attempting to start existing container '$CONTAINER_NAME'..." -ForegroundColor Yellow
    docker start $CONTAINER_NAME 2>$null
    if ($LASTEXITCODE -eq 0) {
        Write-Host "Container started successfully." -ForegroundColor Green
    } else {
        # If we get here, container doesn't exist or can't be started
        Write-Host "Could not start container (it may not exist or is in a bad state). Removing any conflicting container and creating a new one..." -ForegroundColor Yellow
        docker rm -f $CONTAINER_NAME 2>$null
        
        Write-Host "Pulling latest Ganache image..." -ForegroundColor Yellow
        docker pull trufflesuite/ganache:latest
        if ($LASTEXITCODE -ne 0) {
            Write-Host "Failed to pull Ganache image. Check your network connection." -ForegroundColor Red
            exit 1
        }
        
        Write-Host "Starting Ganache with your mnemonic..." -ForegroundColor Yellow
        docker run -d --name $CONTAINER_NAME -p 8545:8545 trufflesuite/ganache:latest `
          --wallet.mnemonic "world reopen cute forward vintage okay drink margin piano buffalo autumn awful" `
          --wallet.totalAccounts 10 `
          --wallet.defaultBalance 1000 `
          --miner.blockGasLimit 30000000 `
          --miner.defaultGasPrice 2000000000 `
          --miner.callGasLimit 50000000 `
          --chain.hardfork shanghai `
          --chain.networkId 1337 `
          --chain.chainId 1337
        
        if ($LASTEXITCODE -ne 0) {
            Write-Host "Failed to start Ganache container." -ForegroundColor Red
            exit 1
        }
    }
}

Write-Host ""
Write-Host "========================================" -ForegroundColor Green
Write-Host "   Ganache is running!" -ForegroundColor Green
Write-Host "========================================" -ForegroundColor Green
Write-Host "RPC URL: http://localhost:8545" -ForegroundColor Cyan
Write-Host "Network ID: 1337" -ForegroundColor Cyan
Write-Host "Chain ID: 1337" -ForegroundColor Cyan
Write-Host "Hardfork: shanghai" -ForegroundColor Cyan
Write-Host "Default Gas Price: 2 Gwei (2000000000 wei)" -ForegroundColor Cyan
Write-Host "Block Gas Limit: 30000000" -ForegroundColor Cyan
Write-Host "Call Gas Limit: 50000000" -ForegroundColor Cyan
Write-Host ""
Write-Host "To view logs: docker logs -f ganache-container" -ForegroundColor Yellow
Write-Host "To stop: docker stop ganache-container" -ForegroundColor Yellow
Write-Host "To remove: docker rm ganache-container" -ForegroundColor Yellow
Write-Host "========================================" -ForegroundColor Green

# Show container status
Start-Sleep -Seconds 2
docker ps --filter name=ganache-container

# Show recent logs
Write-Host "`nRecent logs:" -ForegroundColor Green
docker logs --tail 20 ganache-container