FROM openjdk:17-jdk-slim
VOLUME /tmp
EXPOSE 8080
ADD target/rabbit2-0.0.1-SNAPSHOT.jar rabbit2.jar 
ENTRYPOINT ["java","-jar","/rabbit2.jar"]