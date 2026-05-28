#!/bin/bash

set -e

TAG=v1.0.$(date +"%m%d")

cd ../
./gradlew clean build -x test

API_JAR_FILE="$PWD"/docker/customer-service/customer-service.jar

rm -f "$API_JAR_FILE"

cp customer-service/build/libs/customer-service-1.0.0-SNAPSHOT.jar docker/customer-service/
mv docker/customer-service/customer-service-1.0.0-SNAPSHOT.jar docker/customer-service/customer-service.jar

cd docker/customer-service

docker buildx build --platform=linux/amd64 --no-cache --provenance false -t customer-service:"$TAG" -t customer-service:latest .

echo "Docker customer-service built $TAG"