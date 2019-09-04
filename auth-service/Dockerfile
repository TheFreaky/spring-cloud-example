FROM openjdk:8-jdk-alpine

ARG JAR_FILE=target/auth-service-0.1.jar
ADD ${JAR_FILE} auth-service-0.1.jar

CMD ["java","-jar","/auth-service-0.1.jar"]