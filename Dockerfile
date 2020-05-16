FROM openjdk:14-alpine
RUN addgroup -S spring && adduser -S spring -G spring
USER spring:spring
ENV SPRING_PROFILES_ACTIVE=jenkins
VOLUME /tmp
ARG JAR_FILE=target/*.jar
COPY ${JAR_FILE} app.jar
EXPOSE 8080
ENTRYPOINT ["java","-jar","/app.jar"]