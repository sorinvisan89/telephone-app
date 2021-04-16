FROM maven:3.6.0-jdk-11 AS builder
RUN mkdir -p /app/telephone-app

WORKDIR /app/telephone-app
ADD . /app/telephone-app

RUN mvn clean install
RUN ls -la /app/telephone-app/target/

FROM adoptopenjdk/openjdk11:jre-11.0.3_7-alpine
RUN mkdir -p /app
COPY --from=builder /app/telephone-app/target/*.jar /app/contactAgenda.jar
WORKDIR /app
ENTRYPOINT ["java", "-Djava.security.egd=file:/dev/./urandom", "-jar", "/app/contactAgenda.jar"]
