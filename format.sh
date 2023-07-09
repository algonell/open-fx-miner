#!/bin/sh

clear

echo "google-java-format..."
java -jar ~/Misc/google-java-format-*.jar --replace $(git ls-files ~/git/open-fx-miner/*.java)

echo

echo "SpotBugs..."
mvn -f ~/git/open-fx-miner spotbugs:check
