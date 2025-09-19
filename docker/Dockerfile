FROM ubuntu:latest
LABEL authors="rafael"
ENTRYPOINT ["top", "-b"]

FROM openjdk:17-oracle
ARG JAR_FILE=*.jar
COPY ${JAR_FILE} application.jar
ENTRYPOINT ["java", "-jar", "application.jar"]

