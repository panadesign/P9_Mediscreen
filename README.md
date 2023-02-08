[![forthebadge](https://forthebadge.com/images/badges/made-with-java.svg)](https://forthebadge.com)

# Mediscreen : Une application médicale

Mediscreen travaille avec les cliniques de santé et les cabinets privés pour dépister les risques de développer un diabète

## Technologies
- JAVA
- SPRINGBOOT
- MYSQL
- MONGO DB
- DOCKER
- THYMELEAF


## Auteur

Jérémy Charpentier

-------------------------

## Lancer l'application

- Cloner le projet :
  - HTTPS : https://github.com/panadesign/P9_Mediscreen.git
- Créer images des différents modules avec :
  - `docker build --tag=ms-patient:latest .`
  - `docker build --tag=ms-history:latest .`
  - `docker build --tag=ms-assessment:latest .`
  - `docker build --tag=ms-clientui:latest .`
- Lancer le docker compose pour générer les containers:
  - `docker-compose up -d`

## Accéder aux services
http://localhost:8080

## Accéder aux requêtes via swagger
http://localhost:8081/swagger-ui/index.html

http://localhost:8082/swagger-ui/index.html

http://localhost:8083/swagger-ui/index.html
