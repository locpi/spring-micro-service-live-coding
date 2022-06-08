FROM openjdk:17-alpine
ARG file
ARG version
ARG jarName
ARG JAR_FILE=${file}/target/$jarName-$version.jar
COPY ${JAR_FILE} app.jar
ENTRYPOINT ["java","-jar","/app.jar"]
