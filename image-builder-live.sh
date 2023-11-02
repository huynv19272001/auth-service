#!/bin/sh
TODAY=$(date +%Y%m%d)
IMAGE=dvc-haugiang-service
VERSION=live-$TODAY
REGISTRY=prod-docker-registry.lienvietpostbank.com.vn/esb-ms

FULL_IMAGE=${REGISTRY}/${IMAGE}:${VERSION}

# Build maven
mvn clean install spring-boot:repackage -DskipTests -pl ${IMAGE}

# Build image
echo "Build image: " $FULL_IMAGE

docker build -t ${IMAGE} -f dockerfile/Dockerfile-${IMAGE} .
docker tag ${IMAGE}:latest $FULL_IMAGE
docker push $FULL_IMAGE
