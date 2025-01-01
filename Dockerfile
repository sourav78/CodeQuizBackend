# Use Ubuntu as the base image
FROM ubuntu:latest as build

# Install JDK 21 and Maven
RUN apt-get update && apt-get install -y wget software-properties-common && \
    add-apt-repository ppa:openjdk-r/ppa && \
    apt-get update && \
    apt-get install -y openjdk-21-jdk maven

# Set JAVA_HOME environment variable
ENV JAVA_HOME /usr/lib/jvm/java-21-openjdk-amd64

#Set the working directory
WORKDIR /app

# Copy the pom.xml file and install dependencies
COPY pom.xml .
RUN mvn dependency:go-offline

# Copy the source code and build the application
COPY src ./src
RUN mvn clean package -DskipTests

# Use the official OpenJDK image to run the application
FROM openjdk:21-jdk-slim

#Set the Working Directory
WORKDIR /app

# Copy the JAR file from the builder image
COPY --from=build /app/target/CodeQuizBackend-0.0.1-SNAPSHOT.jar .

#Expose the port
EXPOSE 8080

#Specify the command to run the application
CMD ["java", "-jar", "/app/CodeQuizBackend-0.0.1-SNAPSHOT.jar"]