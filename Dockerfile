# Etapa de build
FROM ubuntu:20.04 AS build

# Atualizar os pacotes e instalar as dependências necessárias
RUN apt-get update && apt-get install -y \
    openjdk-21-jdk \
    wget \
    unzip \
    python3 \
    python3-pip

# Instalar yt-dlp diretamente usando pip3
RUN pip3 install yt-dlp

# Instalar Gradle 8.2.1
RUN wget https://services.gradle.org/distributions/gradle-8.2.1-bin.zip -P /tmp
RUN unzip -d /opt/gradle /tmp/gradle-8.2.1-bin.zip
RUN ln -s /opt/gradle/gradle-8.2.1 /opt/gradle/latest
ENV GRADLE_HOME=/opt/gradle/latest
ENV PATH="${GRADLE_HOME}/bin:${PATH}"

# Copiar o código-fonte do projeto para o contêiner
COPY . .

# Executar o build do projeto com Gradle
RUN gradle clean build --warning-mode all

# Etapa final para execução do jar
FROM openjdk:21-jdk-slim

# Definir a variável de ambiente
ENV KEY=${KEY}

# Expor a porta da aplicação
EXPOSE 8080

# Copiar o arquivo JAR gerado da etapa de build para o contêiner final
COPY --from=build /build/libs/*.jar /app.jar

# Comando de entrada para executar a aplicação
ENTRYPOINT [ "java", "-jar", "/app.jar" ]
