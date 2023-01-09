FROM maven:3.8.6-openjdk-11
EXPOSE 8082
ADD target/*.jar ms-history.jar
ENTRYPOINT ["java","-jar","ms-history.jar"]