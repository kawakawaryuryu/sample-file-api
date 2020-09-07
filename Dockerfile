FROM maven:3.6-adoptopenjdk-11 as build
WORKDIR /build
COPY pom.xml /build/
RUN mvn dependency:go-offline
COPY ./src /build/src/
RUN mvn clean package

FROM adoptopenjdk:11-jre-hotspot
WORKDIR /app
COPY --from=build /build/target ./target

ENTRYPOINT ["java", "-jar", "target/sample-file-api-0.0.1-SNAPSHOT.jar"]
