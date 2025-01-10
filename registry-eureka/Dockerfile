FROM eclipse-temurin:17-jdk-alpine
VOLUME /tmp
ARG SRC=target/registry-eureka-0.0.1.jar
COPY ${SRC} app.jar
ENTRYPOINT ["java", "-jar", "/app.jar"]
