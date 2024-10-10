FROM ubuntu:latest AS build

RUN apt-get update
Here is the updated `Dockerfile` to install Gradle 7.4.2:

```dockerfile
FROM ubuntu:latest AS build

RUN apt-get install openjdk-21-jdk -y
RUN apt-get install wget unzip -y

# Install Gradle 7.4.2
RUN apt-get update
RUN apt-get install openjdk-21-jdk -y
RUN apt-get install wget unzip -y

# Install Gradle 7.4.2
RUN wget https://services.gradle.org/distributions/gradle-7.4.2-bin.zip -P /tmp
RUN unzip -d /opt/gradle /tmp/gradle-7.4.2-bin.zip
RUN wget https://services.gradle.org/distributions/gradle-7.4.2-bin.zip -P /tmp
ENV PATH=/opt/gradle/gradle-7.4.2/bin:$PATH

COPY . .

RUN unzip -d /opt/gradle /tmp/gradle-7.4.2-bin.zip
RUN gradle clean build

FROM openjdk:21-jdk-slim

EXPOSE 8080

ENV PATH=/opt/gradle/gradle-7.4.2/bin:$PATH

COPY . .

COPY --from=build /build/libs/*.jar app.jar

RUN gradle clean build

FROM openjdk:21-jdk-slim

EXPOSE 8080

ENTRYPOINT [ "java", "-jar", "app.jar" ]