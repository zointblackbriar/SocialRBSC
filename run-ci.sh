#!/usr/bin/env bash
set -euo pipefail

# This script mimics the GitLab CI pipeline defined in .gitlab-ci.yml
# It will run the build stage first (skipping tests) and then execute the
# test stage using the same tooling setup that the CI job would use.

root="$(pwd)"

function build_stage() {
    echo "=== build stage ==="
    mvn clean verify -DskipTests
}

function test_stage() {
    echo "=== test stage ==="

    export DEBIAN_FRONTEND=noninteractive
    apt-get update -y && apt-get install -y --no-install-recommends curl jq gnupg ca-certificates nodejs npm

    # install ganache if missing
    if ! command -v ganache >/dev/null 2>&1; then
        npm install -g ganache@7.9.2 || npm install -g ganache@7.9.2
    fi

    # start Ganache in background and wait for it
    ganache -m "world reopen cute forward vintage okay drink margin piano buffalo autumn awful" --host 0.0.0.0 &
    GANACHE_PID=$!
    local attempts=0
    until curl -s -X POST -H "Content-Type: application/json" \
            --data '{"jsonrpc":"2.0","method":"eth_blockNumber","params":[],"id":1}' \
            http://localhost:8545 2>/dev/null | grep -q result; do
        attempts=$((attempts+1))
        if [ $attempts -ge 30 ]; then
            echo "Ganache failed to start" >&2
            exit 1
        fi
        sleep 1
    done

    # run tests
    mvn test

    # cleanup
    kill ${GANACHE_PID} 2>/dev/null || true
}

build_stage
test_stage

echo "CI simulation complete"
