#!/bin/sh

echo "Maven..."
mvn -f ~/git/open-fx-miner versions:use-latest-releases -DgenerateBackupPoms=false
find ~/git/open-fx-miner -name "*.xml" -exec xmllint --format '{}' --output '{}' \;

echo "build..."
~/git/open-fx-miner/build.sh
