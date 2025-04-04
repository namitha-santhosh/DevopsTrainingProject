FROM openjdk:17
COPY Employee-Project/employeeapp/target/springboot-crud-web-app-0.0.1-SNAPSHOT.jar app.jar
ENTRYPOINT ["java", "-jar", "/app.jar"]
