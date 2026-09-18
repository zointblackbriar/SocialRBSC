#!/bin/bash

echo "   Ganache Docker Setup Script"

CONTAINER_NAME="ganache-container"

# Check if container is already running
echo "Checking if container '$CONTAINER_NAME' is already running..."
if docker ps --filter "name=$CONTAINER_NAME" --format "{{.Names}}" | grep -q "^$CONTAINER_NAME$"; then
  echo "Container is already running."
else
  # Try to start an existing container
  echo "Attempting to start existing container '$CONTAINER_NAME'..."
  if docker start "$CONTAINER_NAME" >/dev/null 2>&1; then
    echo "Container started successfully."
  else
    # If we get here, container doesn't exist or can't be started
    echo "Could not start container (it may not exist or is in a bad state). Removing any conflicting container and creating a new one..."
    docker rm -f "$CONTAINER_NAME" >/dev/null 2>&1
    
    echo "Pulling latest Ganache image..."
    if ! docker pull trufflesuite/ganache:latest; then
      echo "Failed to pull Ganache image. Check your network connection."
      exit 1
    fi
    
    echo "Starting Ganache with your mnemonic..."
    docker run -d --name "$CONTAINER_NAME" -p 8545:8545 trufflesuite/ganache:latest \
      --wallet.mnemonic "world reopen cute forward vintage okay drink margin piano buffalo autumn awful" \
      --wallet.totalAccounts 10 \
      --wallet.defaultBalance 1000 \
      --miner.blockGasLimit 30000000 \
      --miner.defaultGasPrice 2000000000 \
      --miner.callGasLimit 50000000 \
      --chain.hardfork shanghai \
      --chain.networkId 1337 \
      --chain.chainId 1337
    
    if [ $? -ne 0 ]; then
      echo "Failed to start Ganache container."
      exit 1
    fi
  fi
fi

# label for info display

echo ""
echo "========================================"
echo "   Ganache is running!"
echo "========================================"
echo "RPC URL: http://localhost:8545"
echo "Network ID: 1337"
echo "Chain ID: 1337"
echo "Hardfork: shanghai"
echo "Default Gas Price: 2 Gwei (2000000000 wei)"
echo "Block Gas Limit: 30000000"
echo "Call Gas Limit: 50000000"
echo ""
echo "To view logs: docker logs -f ganache-container"
echo "To stop: docker stop ganache-container"
echo "To remove: docker rm ganache-container"
echo "========================================"

# Show container status
sleep 2
docker ps --filter name=ganache-container

# Show recent logs
echo ""
echo "Recent logs:"
docker logs --tail 20 ganache-container