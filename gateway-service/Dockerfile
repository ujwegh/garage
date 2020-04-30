FROM openjdk:11.0.7-jdk
ADD target/gateway-service-0.0.1-SNAPSHOT.jar gateway-service.jar
ENTRYPOINT ["java", "-jar", "/gateway-service-0.0.1-SNAPSHOT.jar"]
EXPOSE 8765