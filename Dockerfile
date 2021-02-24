#Pulling from openjdk to get Java 14
FROM openjdk:15-jdk-alpine
#Metadata
LABEL author="Thomas Driscoll"
#Environment variables
ENV CONFIG_SERVER_URL=${CONFIG_SERVER_URL}
ENV CONFIG_USER_NAME=${CONFIG_USER_NAME}
ENV CONFIG_PASSWORD=${CONFIG_PASSWORD}
#Copies all source code in same folder - . - to docker virtual fs folder, /app
COPY . /app
#Working director where instructions will run
WORKDIR /app
#Allow Docker to write to files to host
VOLUME ["/app"]
#Run ./gradlew build
RUN ./gradlew build
#Port mapping - HOST_PORT:CONTAINER_PORT
EXPOSE 8018
EXPOSE 80
ENTRYPOINT ["java", "-jar", "/app/build/libs/javatemplate-0.0.1-SNAPSHOT.jar"]
