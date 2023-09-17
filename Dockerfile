FROM gradle:7.5.1-jdk17-alpine AS build
COPY --chown=gradle:gradle . /home/gradle/src
WORKDIR /home/gradle/src
RUN gradle clean
RUN gradle build

FROM eclipse-temurin:17-alpine
FROM selenium/standalone-firefox:latest
COPY --from=build /home/gradle/src/build/libs/demo-0.0.1-SNAPSHOT.jar demo.jar

ENTRYPOINT ["java","-jar","demo.jar"]
