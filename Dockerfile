FROM maven:3.9.9-eclipse-temurin-17

WORKDIR /app

COPY pom.xml .
COPY src ./src

CMD sh -c "mvn clean test -Dgroups=$TEST_GROUP"