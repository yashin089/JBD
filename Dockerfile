# Stage 1 - build
# Use the official Maven image to build the application
FROM maven:3.8.6-eclipse-temurin-17 AS build
# Set the working directory
WORKDIR /app
# Copy the pom.xml and download dependencies
COPY pom.xml .
#RUN mvn dependency:go-offline -B
COPY src ./src
RUN mvn clean package -DskipTests

# Stage 2 - run
# Use image to run the application
FROM openjdk:17.0.2-jdk
# Set the working directory
WORKDIR /app
# Copy the jar file from the previosstage
COPY --from=build /app/target/*.jar app.jar
#COPY --from=build /app/src/main/resources /app/
# Expose the application port
EXPOSE 8080

# Run the application
ENTRYPOINT ["java", "-jar", "app.jar"]