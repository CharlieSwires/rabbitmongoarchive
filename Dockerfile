FROM openjdk:17-jdk-slim
VOLUME /tmp
EXPOSE 8080
ADD target/rabbit-0.0.1-SNAPSHOT.jar rabbit.jar 
ENTRYPOINT ["java","-jar","/rabbit.jar"]