FROM openjdk:7-jre-alpine
VOLUME /tmp
EXPOSE 8080
ARG WAR_FILE
ADD ${WAR_FILE} app.war
ENTRYPOINT ["java","-jar","/app.war"]
