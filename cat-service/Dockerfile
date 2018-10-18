FROM openjdk:8-jdk-alpine

EXPOSE 8080

ARG JAR_FILE=target/cat-service-0.1.jar

ADD ${JAR_FILE} cat-service-0.1.jar

CMD ["java","-jar","/cat-service-0.1.jar"]