#!/usr/bin/env bash
cd auth-service/ || exit
mvn clean package
cd ..
cd cat-service/ || exit
mvn clean package
cd ..
cd config-server/ || exit
mvn clean package
cd ..
cd service-registry/ || exit
mvn clean package
cd ..
cd user-service/ || exit
mvn clean package
cd ..
cd web-service/ || exit
mvn clean package
cd ..
cd zuul-service/ || exit
mvn clean package