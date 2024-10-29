FROM openjdk:17-jdk-slim

ENV TZ=Europe/Rome

EXPOSE 8080

ADD target/codemotion.jar /app/codemotion.jar
CMD java -jar /app/codemotion.jar -Dserver.port=8080
