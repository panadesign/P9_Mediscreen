FROM maven:3.8.6-openjdk-11
EXPOSE 8081
ADD target/*.jar ms-patient.jar
ENTRYPOINT ["java","-jar","ms-patient.jar"]