#!/usr/bin/env bash
cd auth-service/
mvn clean package
cd ..
cd cat-service/
mvn clean package
cd ..
cd service-registry/
mvn clean package
cd ..
cd user-service/
mvn clean package
cd ..
cd web-service/
mvn clean package
cd ..
cd zuul-service/
mvn clean package
cd ..