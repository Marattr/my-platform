#!/bin/bash

set -e

TAG=v1.0.$(date +"%m%d")

cd ../
./gradlew clean build -x test

API_JAR_FILE="$PWD"/docker/api-gateway/api-gateway.jar

rm -f "$API_JAR_FILE"

cp api-gateway/build/libs/api-gateway-1.0.0-SNAPSHOT.jar docker/api-gateway/
mv docker/api-gateway/api-gateway-1.0.0-SNAPSHOT.jar docker/api-gateway/api-gateway.jar

cd docker/api-gateway

docker buildx build --platform=linux/amd64 --no-cache --provenance false -t api-gateway:"$TAG" -t api-gateway:latest .

echo "Docker api-gateway built $TAG"