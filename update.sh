#!/bin/sh

echo "Maven..."
mvn -f ~/git/open-fx-miner versions:use-latest-releases

echo "build..."
~/git/open-fx-miner/build.sh
