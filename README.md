[![forthebadge](https://forthebadge.com/images/badges/made-with-java.svg)](https://forthebadge.com)

# Mediscreen : Une application médicale

Mediscreen travaille avec les cliniques de santé et les cabinets privés pour dépister les risques de développer un diabète

## Technologies
- JAVA
- SPRINGBOOT
- MYSQL
- MONGO DB
- DOCKER


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

## Accéder aux service

http://localhost:8080

