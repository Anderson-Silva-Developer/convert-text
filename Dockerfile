FROM ubuntu:20.04 AS build


RUN apt-get update && apt-get install -y \
    openjdk-21-jdk \
    wget \
    unzip


RUN wget https://services.gradle.org/distributions/gradle-8.2.1-bin.zip -P /tmp
RUN unzip -d /opt/gradle /tmp/gradle-8.2.1-bin.zip
RUN ln -s /opt/gradle/gradle-8.2.1 /opt/gradle/latest
ENV GRADLE_HOME=/opt/gradle/latest
ENV PATH="${GRADLE_HOME}/bin:${PATH}"

COPY . .

RUN gradle clean build --warning-mode all

FROM openjdk:21-jdk-slim

ENV KEY=${KEY}

EXPOSE 8080

COPY --from=build /build/libs/*.jar /app.jar

ENTRYPOINT [ "java", "-jar", "/app.jar" ]
