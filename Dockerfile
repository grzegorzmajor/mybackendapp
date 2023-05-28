FROM eclipse-temurin:17-jre-alpine
COPY /target/mybackendapp.jar /mybackendapp.jar
ENTRYPOINT ["java","-jar","/mybackendapp.jar"]