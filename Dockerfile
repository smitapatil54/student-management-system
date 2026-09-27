FROM eclipse-temurin:17-jre

COPY target/student-management-system-1.0.jar /app/student-management-system.jar

WORKDIR /app

CMD ["java", "-jar", "student-management-system.jar"]