FROM maven:3.8.6-openjdk-11
EXPOSE 8081
ADD target/*.jar ms_patient.jar
ENTRYPOINT ["java","-jar","ms_patient.jar"]