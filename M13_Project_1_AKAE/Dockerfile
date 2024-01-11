FROM eclipse-temurin:21.0.1_12-jre-jammy
ARG JAR_FILE=target/*.jar
COPY ${JAR_FILE} app.jar
ENTRYPOINT ["java","-jar","/app.jar"]


#docker build -t springbootapp:1.0 . -> para crear imagen
#docker image list
#docker run -p 8080:8080 --name springapp -d -t springbootapp:1.0

#docker ps -> ver contenedores en marcha



#docker stop springapp -> para pararlo
#docker start springapp -> arrancar

#docker stats -> cuanto ocupa 

#docker rm springapp

#docker login
#docker tag springbootapp:1.0 aleksandra2211/springbootapp:1.0
#docker push aleksandra2211/springbootapp:1.0

#desde un servidor
#1->descargas imagen -> docker pull aleksandra2211/springbootapp:1.0
#2->ejecutas imagen ->docker run -p 8080:8080 --name springapp -d -t aleksandra2211/springbootapp:1.0
