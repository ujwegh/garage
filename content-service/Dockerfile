FROM openjdk:11.0.7-jdk
ADD target/content-service-0.0.1-SNAPSHOT.jar content-service.jar
ENTRYPOINT ["java", "-jar", "/content-service-0.0.1-SNAPSHOT.jar"]
EXPOSE 8081