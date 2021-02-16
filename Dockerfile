FROM openjdk:11-oraclelinux7
ARG JAR_FILE=target/*.jar
COPY ${JAR_FILE} stay_awhile_and_listen-0.0.1-SNAPSHOT.jar
ENTRYPOINT ["java","-jar","/stay_awhile_and_listen-0.0.1-SNAPSHOT.jar"]
EXPOSE 8888