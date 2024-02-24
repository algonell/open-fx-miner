#!/bin/sh

clear

echo
echo "open-fx-miner..."
mvn -f ~/git/open-fx-miner clean package -DskipTests
