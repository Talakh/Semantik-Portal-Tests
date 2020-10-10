FROM openjdk:8-jdk-alpine
VOLUME /tmp
EXPOSE 8081
ADD target/server-0.0.1-SNAPSHOT.jar server-0.0.1-SNAPSHOT.jar
ENTRYPOINT ["java","-Djava.security.egd=file:/dev/./urandom","-jar","/server-0.0.1-SNAPSHOT.jar"]
