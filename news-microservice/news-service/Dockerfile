FROM openjdk:17-alpine

COPY ./target/news-service-0.0.1-SNAPSHOT.jar /newsapp/
WORKDIR /newsapp

CMD ["java", "-jar", "news-service-0.0.1-SNAPSHOT.jar"]