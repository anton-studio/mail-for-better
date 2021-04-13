FROM maven:3.5.2-jdk-8-alpine AS MAVEN_ENV
WORKDIR /build/
COPY pom.xml /build
COPY src /build/src
RUN mvn clean package -DskipTests=true

FROM openjdk:8-jre-alpine
COPY  --from=MAVEN_ENV /target/renren-fast.jar app.jar
EXPOSE 6000
ENTRYPOINT ["java", "-jar", "app.jar"]