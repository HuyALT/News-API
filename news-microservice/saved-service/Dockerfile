FROM openjdk:17-alpine

COPY ./target/saved-service-0.0.1-SNAPSHOT.jar /newsapp/
WORKDIR /newsapp

CMD ["java", "-jar", "saved-service-0.0.1-SNAPSHOT.jar"]