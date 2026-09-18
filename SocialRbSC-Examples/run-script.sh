#!/bin/bash

mvn clean compile
ganache -m "world reopen cute forward vintage okay drink margin piano buffalo autumn awful" &
mvn web3j:generate-sources
mvn clean install -DskipTests=true