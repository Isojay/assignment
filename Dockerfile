FROM openjdk:17-jdk-alpine
COPY target/*.jar app.jar
ENTRYPOINT ["java","-jar","/app.jar","com.assignment.AssignmentApplication"]
EXPOSE 8080