FROM gradle:jdk19-jammy AS build
COPY --chown=gradle:gradle . /home/gradle/src
WORKDIR /home/gradle/src
RUN gradle build

FROM eclipse-temurin:19-jdk-alpine
FROM selenium/standalone-firefox:latest
COPY --from=build /home/gradle/src/build/libs/demo-0.0.1-SNAPSHOT.jar demo.jar

ENTRYPOINT ["java","-jar","demo.jar"]
