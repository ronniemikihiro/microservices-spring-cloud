FROM adoptopenjdk/openjdk11:ubi
ARG JAR_FILE=target/*.jar
COPY ${JAR_FILE} api-user-data.jar
ENTRYPOINT ["java", "-jar", "api-user-data.jar"]