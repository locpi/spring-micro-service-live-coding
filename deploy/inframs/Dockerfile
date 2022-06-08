FROM adoptopenjdk/openjdk11
ARG file
COPY ${file} app.jar
ENTRYPOINT ["java","-jar","app.jar"]
