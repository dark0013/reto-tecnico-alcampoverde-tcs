FROM openjdk:17-jdk-alpine
WORKDIR /app
COPY target/ms-customer-bnk-0.0.1-SNAPSHOT.jar /app/app.jar
EXPOSE 9595
ENTRYPOINT ["java", "-jar", "app.jar"]