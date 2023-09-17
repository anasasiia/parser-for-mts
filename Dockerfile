FROM openjdk:17-jdk-alpine
FROM selenium/standalone-firefox:latest
ARG JAR_FILE=build/libs/*.jar
COPY / .
ENTRYPOINT ["java","-jar","build/libs/demo-0.0.1-SNAPSHOT.jar"]