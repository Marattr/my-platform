#!/bin/bash

set -e

TAG=v1.0.$(date +"%m%d")

cd ../
./gradlew clean build -x test

API_JAR_FILE="$PWD"/docker/currency-service/currency-service.jar

rm -f "$API_JAR_FILE"

cp currency-service/build/libs/currency-service-1.0.0-SNAPSHOT.jar docker/currency-service/
mv docker/currency-service/currency-service-1.0.0-SNAPSHOT.jar docker/currency-service/currency-service.jar

cd docker/currency-service

docker buildx build --platform=linux/amd64 --no-cache --provenance false -t currency-service:"$TAG" -t currency-service:latest .

echo "Docker currency-service built $TAG"