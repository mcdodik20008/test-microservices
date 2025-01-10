FROM eclipse-temurin:17-jdk-alpine
VOLUME /tmp
ARG JAR=target/api-gateway-0.0.1.jar
COPY ${JAR} app.jar
ENTRYPOINT ["java", "-jar", "/app.jar"]
