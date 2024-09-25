# Use official Maven image to build the app
FROM maven:3.9.9-eclipse-temurin-21 AS build
WORKDIR /app

# Copy pom.xml and download dependencies
COPY pom.xml .
RUN mvn dependency:go-offline -B

# Copy source code
COPY src ./src

# Package the application
RUN mvn clean package -DskipTests

# Use a lightweight JDK runtime image
FROM eclipse-temurin:21-jdk-alpine
VOLUME /tmp
COPY --from=build /app/target/book-api-0.0.1-SNAPSHOT.jar app.jar
# Expose port
EXPOSE 8080

# Run the application
ENTRYPOINT ["java","-jar","/app.jar"]
