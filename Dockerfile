FROM openjdk:8-jdk-alpine
ARG JAR_FILE=./build/libs/*.jar
COPY ${JAR_FILE} your-community.jar
ENTRYPOINT ["java","-Djava.security.egd=file:/dev/./urandom","-jar","/your-community.jar"]