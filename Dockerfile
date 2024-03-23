FROM amazoncorretto:17-alpine-jdk
COPY target/patient-visit-service-0.0.1-SNAPSHOT.jar patient-visit-service-0.0.1-SNAPSHOT.jar
EXPOSE 8080
ENTRYPOINT ["java","-jar","/patient-visit-service-0.0.1-SNAPSHOT.jar"]