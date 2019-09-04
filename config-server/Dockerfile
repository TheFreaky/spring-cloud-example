FROM openjdk:8-jdk-alpine

ARG JAR_FILE=target/config-server-0.1.jar
ADD ${JAR_FILE} config-server-0.1.jar

CMD ["java","-jar","/config-server-0.1.jar"]