#!/usr/bin/env bash
# build.sh

# Maven kurulumu ve JAR oluşturma
apt-get update && apt-get install -y maven
mvn clean package