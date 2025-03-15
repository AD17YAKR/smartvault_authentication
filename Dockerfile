# Use Java 17 as the base image
FROM eclipse-temurin:17-jdk-jammy

# Set working directory
WORKDIR /app

# Copy the Maven files first (for better caching)
COPY mvnw .
COPY .mvn .mvn
COPY pom.xml .
COPY keystore.p12 /app/keystore.p12

# Copy the source code
COPY src src

# Build the application
RUN ./mvnw package -DskipTests

# Use a smaller runtime image
FROM eclipse-temurin:17-jre-jammy

WORKDIR /app

# Copy the built jar from the build stage
COPY --from=0 /app/target/*.jar app.jar



# Expose the port your application runs on (typically 8080)
EXPOSE 8080

# Command to run the application
ENTRYPOINT ["java", "-jar", "app.jar"]
