#!/bin/bash

set -e

TAG=v1.0.$(date +"%m%d")

cd ../
./gradlew clean build -x test

API_JAR_FILE="$PWD"/docker/config-server/config-server.jar

rm -f "$API_JAR_FILE"

cp config-server/build/libs/config-server-1.0.0-SNAPSHOT.jar docker/config-server/
mv docker/config-server/config-server-1.0.0-SNAPSHOT.jar docker/config-server/config-server.jar

cd docker/config-server

docker buildx build --platform=linux/amd64 --no-cache --provenance false -t config-server:"$TAG" -t config-server:latest .

echo "Docker config-server built $TAG"