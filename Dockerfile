FROM openjdk:11
COPY ./build/libs/learn-eng-0.0.1-SNAPSHOT.jar learn-eng-0.0.1-SNAPSHOT.jar
WORKDIR /
ENTRYPOINT ["java", "-jar", "learn-eng-0.0.1-SNAPSHOT.jar"]