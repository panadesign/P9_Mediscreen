FROM maven:3.8.6-openjdk-11
EXPOSE 8080
ADD target/*.jar ms-clientui.jar
ENTRYPOINT ["java","-jar","ms-clientui.jar"]