FROM openjdk:11.0.9-jre-slim

ARG JAR_FILE
COPY target/${JAR_FILE} app.jar

EXPOSE 8001
EXPOSE 8002

## Override it with: java -jar /app.jar
## Default remote-debug on
CMD ["java", "-Xdebug", "-agentlib:jdwp=transport=dt_socket,server=y,suspend=n,address=*:8002", "-jar", "/app.jar"]
# ENTRYPOINT ["java", "-jar", "/app.jar"]
