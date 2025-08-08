FROM openjdk:17-jdk-alpine
WORKDIR /app
COPY target/ms-account-movement-0.0.1-SNAPSHOT.jar /app/app.jar
EXPOSE 9596
ENTRYPOINT ["java", "-jar", "app.jar"]