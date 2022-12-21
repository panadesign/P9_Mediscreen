FROM maven:3.8.6-openjdk-11
EXPOSE 8081
ADD target/ms_patient-0.0.1-SNAPSHOT.jar ms_patient-0.0.1-SNAPSHOT.jar
ENTRYPOINT ["java","-jar","ms_patient-0.0.1-SNAPSHOT.jar"]