
FROM maven:3.8.6-openjdk-11
EXPOSE 8083
ADD target/*.jar ms-assessment.jar
ENTRYPOINT ["java","-jar","ms-assessment.jar"]