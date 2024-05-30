FROM openjdk:17-alpine

COPY ./target/api-gateway-0.0.1-SNAPSHOT.jar /news-app/
WORKDIR /news-app

CMD ["java", "-jar", "api-gateway-0.0.1-SNAPSHOT.jar"]

EXPOSE 8761