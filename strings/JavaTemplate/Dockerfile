FROM maven:3.6.1-jdk-11 AS MAVEN_BUILD
COPY ./ ./
RUN mvn clean package

ENV TEXT_PATH=src/main/java/resources
ENV LIMIT=40
ENV JUSTIFY=true

FROM openjdk:11
COPY --from=MAVEN_BUILD target/*.jar /StringFormatter.jar
ENTRYPOINT ["java", "-jar", "/StringFormatter.jar", "${TEXT_PATH} ${LIMIT} ${JUSTIFY}"]
