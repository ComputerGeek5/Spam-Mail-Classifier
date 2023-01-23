FROM openjdk:8

EXPOSE 8000

WORKDIR /app

COPY ./target/Spam-Classifier-0.0.1-SNAPSHOT.jar SpamClassifier.jar

ENTRYPOINT ["java", "-jar", "SpamClassifier.jar"]