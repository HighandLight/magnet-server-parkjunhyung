# build stage
FROM eclipse-temurin:21-jdk AS build
WORKDIR /app

COPY . .
RUN chmod +x ./gradlew
RUN ./gradlew bootJar --no-daemon

# runtime stage
FROM eclipse-temurin:21-jre
WORKDIR /app

RUN useradd -m appuser

COPY --from=build /app/build/libs/*.jar app.jar
RUN chown appuser:appuser /app/app.jar

USER appuser

EXPOSE 8080
ENV JAVA_OPTS=""

ENTRYPOINT ["sh", "-c", "java $JAVA_OPTS -jar /app/app.jar"]