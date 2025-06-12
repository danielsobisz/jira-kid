# Build stage
FROM amazoncorretto:17-alpine AS build


WORKDIR /app

# Copy Gradle wrapper and build files
COPY gradlew .
COPY gradle gradle
COPY build.gradle .
COPY settings.gradle .

# Make gradlew executable
RUN chmod +x ./gradlew

# Copy source code
COPY src src

# Build only the bootJar (executable jar)
RUN ./gradlew bootJar -x test

# Runtime stage
FROM amazoncorretto:17-alpine

WORKDIR /app

# Copy ALL jars and then select the right one
COPY --from=build /app/build/libs/*.jar ./
RUN ls -la && mv $(ls *.jar | grep -v plain | head -1) app.jar && rm -f *-plain.jar

# Create non-root user for security
RUN addgroup -g 1001 -S spring && adduser -u 1001 -S spring -G spring
USER spring:spring

# Expose port (adjust if your app uses a different port)
EXPOSE 8080

# Run the application
ENTRYPOINT ["java", "-jar", "app.jar"]