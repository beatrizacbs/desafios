FROM maven:3.6.1-jdk-11 AS MAVEN_BUILD
COPY ./ ./
RUN mvn clean package

ENV TEXT "In the beginning God created the heavens and the earth. Now the earth was formless and empty, darkness was over the surface of the deep, and the Spirit of God was hovering over the waters.\\n\\nAnd God said, \\"Let there be light,\\" and there was light. God saw that the light was good, and he separated the light from the darkness. God called the light \\"day,\\" and the darkness he called \\"night.\\" And there was evening, and there was morning - the first day."
ENV LIMIT 40
ENV JUSTIFY true

FROM openjdk:11
COPY --from=MAVEN_BUILD target/*.jar /StringFormatter.jar
ENTRYPOINT ["java", "-jar", "/StringFormatter.jar"]
