FROM maven:3.9.9-eclipse-temurin-17

WORKDIR /app

COPY pom.xml .
COPY src ./src

CMD sh -c "rm -rf /app/allure-results/* && mkdir -p /app/allure-results && mvn clean test"