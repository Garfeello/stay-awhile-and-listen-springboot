FROM openjdk:11-oraclelinux7
ARG WAR_FILE=target/*.war
COPY ${WAR_FILE} stay_awhile_and_listen-0.0.1-SNAPSHOT.war
ENTRYPOINT ["java","-jar","/stay_awhile_and_listen-0.0.1-SNAPSHOT.war"]
EXPOSE 8888