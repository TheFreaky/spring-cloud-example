FROM openjdk:8-jdk-alpine

ARG JAR_FILE=target/user-service-0.1.jar
ADD ${JAR_FILE} user-service-0.1.jar

CMD ["java","-jar","/user-service-0.1.jar"]