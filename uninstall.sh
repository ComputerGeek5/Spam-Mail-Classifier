#!/bin/bash

docker rm -f spam_classifier_front
docker rmi spam_classifier_front:1.0

docker rm -f spam_classifier_back
docker rmi spam_classifier_back:1.0

docker rm -f spam_classifier_db
docker rmi spam_classifier_db:1.0

docker rm -f spam_classifier_script
docker rmi spam_classifier_script:1.0

docker network rm spam_classifier_network