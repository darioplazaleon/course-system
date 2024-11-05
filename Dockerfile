FROM amazoncorretto:22

COPY target/course-system-0.0.1-SNAPSHOT.jar app.jar

ENTRYPOINT ["java","-jar","/app.jar"]