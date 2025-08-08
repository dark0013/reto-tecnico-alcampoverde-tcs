FROM openjdk:17-jdk-alpine
WORKDIR /app
COPY target/ms-client-banking-0.0.1-SNAPSHOT.jar /app/app.jar
EXPOSE 9595
ENTRYPOINT ["java", "-jar", "app.jar"]