FROM openjdk:17-jdk-alpine
FROM selenium/standalone-firefox:latest
RUN ./gradlew build
COPY --from=build ./build/libs/demo-0.0.1-SNAPSHOT.jar demo.jar
ENTRYPOINT ["java","-jar","demo.jar"]