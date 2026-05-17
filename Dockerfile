FROM eclipse-temurin:17-jdk-alpine

COPY gradlew .
COPY gradle gradle
COPY build.gradle .
COPY settings.gradle .

RUN chmod +x ./gradlew

RUN ./gradlew dependencies --no-daemon
COPY src src
RUN ./gradlew build --exclude-task test --no-daemon
RUN mv ./build/libs/*-SNAPSHOT.jar ./app.jar

EXPOSE 8080
ENTRYPOINT ["java", "-jar", "-Dspring.profiles.active=prod", "app.jar"]