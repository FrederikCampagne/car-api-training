FROM openjdk:8-jre-alpine

COPY /build/libs/car-registration-0.0.1-SNAPSHOT.jar /opt/

ENTRYPOINT ["java", "-jar", "/opt/car-registration-0.0.1-SNAPSHOT.jar"]
EXPOSE 8888