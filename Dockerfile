FROM openjdk:17-jdk-alpine
FROM selenium/standalone-firefox:latest
ARG JAR_FILE=build/libs/*.jar
COPY . .
#COPY --from=build ./build/libs/demo-0.0.1-SNAPSHOT.jar demo.jar
CMD java -Xmx256m -jar build/lib/demo-*.jar --spring.profiles.active=default