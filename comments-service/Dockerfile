FROM openjdk:17-alpine

COPY ./target/comments-service-0.0.1-SNAPSHOT.jar /newsapp/
WORKDIR /newsapp

CMD ["java", "-jar", "comments-service-0.0.1-SNAPSHOT.jar"]
