FROM maven:3.9.11-eclipse-temurin-17 AS build

WORKDIR /workspace/app

COPY pom.xml ./
COPY src ./src

RUN mvn -B -DskipTests clean package

FROM eclipse-temurin:17-jre-alpine

RUN addgroup -S spring && adduser -S spring -G spring

WORKDIR /app

COPY --from=build /workspace/app/target/orderdesk-0.1.0.jar ./app.jar

EXPOSE 8080

USER spring:spring

ENTRYPOINT ["java", "-jar", "/app/app.jar"] 
