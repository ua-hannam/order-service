FROM openjdk:17-ea-11-jdk-slim
VOLUME /tmp
COPY build/libs/order-0.0.1-SNAPSHOT.jar orderService.jar
ENTRYPOINT ["java", "-jar", "orderService.jar"]
