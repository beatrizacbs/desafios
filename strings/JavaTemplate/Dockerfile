FROM maven:3.6.1-jdk-11 AS MAVEN_BUILD
COPY ./ ./
RUN mvn clean package

FROM openjdk:11
COPY --from=MAVEN_BUILD target/StringFormatter-1.0.0-SNAPSHOT.jar /StringFormatter.jar
CMD ["java", "-jar", "/StringFormatter.jar"]