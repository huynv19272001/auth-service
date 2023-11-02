#!/bin/sh
#TODAY=$(date +%Y%m%d)
IMAGE=viettel-mm-service
VERSION=dev-1.0.0
REGISTRY=harbor-registry.lienvietpostbank.com.vn:5443/middleware

FULL_IMAGE=${REGISTRY}/${IMAGE}:${VERSION}

# Build maven
mvn clean install spring-boot:repackage -DskipTests -pl ${IMAGE}

# Build image
echo "Build image: " $FULL_IMAGE

docker build -t ${IMAGE} -f dockerfile/Dockerfile-${IMAGE} .
docker tag ${IMAGE}:latest $FULL_IMAGE
docker push $FULL_IMAGE
