FROM openjdk:8-jdk-alpine

EXPOSE 8761

ARG JAR_FILE=target/service-registry-0.1.jar

ADD ${JAR_FILE} service-registry-0.1.jar

CMD ["java","-jar","/service-registry-0.1.jar"]