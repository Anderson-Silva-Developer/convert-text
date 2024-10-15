# Dockerfile

FROM ubuntu:latest AS build

RUN apt-get update && apt-get install -y \
    openjdk-21-jdk \
    wget \
    unzip \
    python3 \
    python3-pip \
    python3-venv

# Criar e ativar um ambiente virtual
RUN python3 -m venv /opt/venv
ENV PATH="/opt/venv/bin:$PATH"

# Instalar yt-dlp no ambiente virtual
RUN pip install yt-dlp

# Instalar Gradle 8.2.1
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

COPY --from=build /build/libs/*.jar app.jar

ENTRYPOINT [ "java", "-jar", "app.jar" ]
