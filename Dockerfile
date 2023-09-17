FROM gradle:jdk17-jammy AS build
COPY --chown=gradle:gradle . /home/gradle/src
WORKDIR /home/gradle/src
RUN gradle build --warning-mode all

FROM eclipse-temurin:17-jdk-jammy
FROM selenium/standalone-firefox:latest
COPY --from=build /home/gradle/src/build/libs/demo-0.0.1-SNAPSHOT.jar demo.jar

ENTRYPOINT ["java","-jar","demo.jar"]
