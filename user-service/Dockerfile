FROM openjdk:17-alpine

COPY ./target/user-service-0.0.1-SNAPSHOT.jar /newsapp/
WORKDIR /newsapp

CMD ["java", "-jar", "user-service-0.0.1-SNAPSHOT.jar"]