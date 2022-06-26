FROM openjdk:11.0.9-jre-slim

ARG JAR_FILE
COPY target/${JAR_FILE} app.jar

## Override it with: java -jar /app.jar
## Default remote-debug on
CMD ["java", "-Xdebug", "-agentlib:jdwp=transport=dt_socket,server=y,suspend=n,address=*:8010", "-jar", "/app.jar"]
