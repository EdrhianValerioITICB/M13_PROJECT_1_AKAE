FROM eclipse-temurin:21.0.1_12-jre-jammy
ARG JAR_FILE=target/*.jar
COPY ${JAR_FILE} app.jar
ENTRYPOINT {"java","-jar","/app.jar"]


#docker build -t springbootapp:1.0 .