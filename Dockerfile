FROM openjdk:17-jdk-alpine
FROM selenium/standalone-firefox:latest
ARG JAR_FILE=build/libs/*.jar
COPY build/libs/demo-0.0.1-SNAPSHOT.jar app.jar
ENTRYPOINT ["java","-jar","/app.jar"]