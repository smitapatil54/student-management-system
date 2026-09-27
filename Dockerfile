FROM tomcat:10.1-jdk17-temurin

COPY target/student-management-system.war /usr/local/tomcat/webapps/student-management-system.war

EXPOSE 8080

CMD ["catalina.sh", "run"]