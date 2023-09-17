FROM openjdk:19-alpine
FROM selenium/standalone-firefox:latest
COPY /build/libs/demo-0.0.1-SNAPSHOT.jar demo.jar
ENTRYPOINT ["java","-jar","demo.jar"]
