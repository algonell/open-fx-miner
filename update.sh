#!/bin/sh

echo "Maven..."
mvn -f ~/git/open-fx-miner versions:use-latest-releases -DgenerateBackupPoms=false

echo "build..."
~/git/open-fx-miner/build.sh
