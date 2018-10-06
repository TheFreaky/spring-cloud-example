FROM openjdk:8-jdk-alpine

EXPOSE 8080

ARG JAR_FILE=target/web-service-0.1.jar

ADD ${JAR_FILE} web-service-0.1.jar

CMD ["java","-jar","/web-service-0.1.jar"]