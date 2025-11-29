# =================================================================
# Stage 1: Build the application using Maven
# =================================================================
FROM maven:3.9-eclipse-temurin-21 AS build

# Set the working directory
WORKDIR /app

# Copy the Maven project file and download dependencies
COPY pom.xml .
RUN mvn dependency:go-offline

# Copy the rest of the source code
COPY src ./src

# Build the application, skipping the tests for faster builds
RUN mvn clean install -DskipTests

# =================================================================
# Stage 2: Create the final, lightweight production image
# =================================================================
FROM eclipse-temurin:21-jre

# Set the working directory
WORKDIR /app

# Copy the executable JAR from the build stage
COPY --from=build /app/target/*.jar app.jar

# Expose the port the application runs on
EXPOSE 1302

# Set the command to run the application
# We will use environment variables in Render to set the profile
ENTRYPOINT ["java", "-jar", "app.jar"]
