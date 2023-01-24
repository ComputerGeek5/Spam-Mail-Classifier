#!/bin/bash

docker network create spam_classifier_network

docker build --no-cache -t spam_classifier_front:1.0 ./Front/
docker run -d -p 80:80 --name spam_classifier_front --network spam_classifier_network -t spam_classifier_front:1.0

docker run -d -p 9000:3306 --name spam_classifier_db --network spam_classifier_network -e MYSQL_ROOT_PASSWORD=root -e MYSQL_DATABASE=spam_db -e MYSQL_USER=back -e MYSQL_PASSWORD=back -t mysql:8.0

docker build -t spam_classifier_back:1.0 ./Back/
docker run -d -p 9001:8000 --name spam_classifier_back --network spam_classifier_network -t spam_classifier_back:1.0

docker build -t spam_classifier_script:1.0 ./Script/
docker run -d -p 9002:7000 --name spam_classifier_script --network spam_classifier_network -t spam_classifier_script:1.0