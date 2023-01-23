#!/bin/bash

docker network create spam_classifier_network

docker build -t spam_classifier_back:1.0 ./Front/
docker run -d -p 4000:4000 --name spam_classifier_front --network spam_classifier_network -t spam_classifier_front:1.0

docker run -d -p 3306:3306 --name spam_classifier_db --network spam_classifier_network -e MYSQL_ROOT_PASSWORD=root -e MYSQL_DATABASE=spam_db mysql:8.0

docker build -t spam_classifier_back:1.0 ./Back/
docker run -d -p 8000:8000 --name spam_classifier_back --network spam_classifier_network -t spam_classifier_back:1.0

docker build -t spam_classifier_script:1.0 ./Script/
docker run -d -p 7000:7000 --name spam_classifier_script --network spam_classifier_network -t spam_classifier_script:1.0