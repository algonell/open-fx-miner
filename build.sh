#!/bin/sh

clear

echo "\nopen-fx-miner..."
mvn -f ~/git/open-fx-miner clean package -DskipTests
