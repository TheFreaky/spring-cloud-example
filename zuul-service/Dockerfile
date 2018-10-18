FROM openjdk:8-jdk-alpine

EXPOSE 8090

ARG JAR_FILE=target/zuul-service-0.1.jar

ADD ${JAR_FILE} zuul-service-0.1.jar

CMD ["java","-jar","/zuul-service-0.1.jar"]