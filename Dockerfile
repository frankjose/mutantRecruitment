FROM  gradle:7.4-jdk11 AS build
COPY --chown=gradle:gradle . /home/gradle/src
WORKDIR /home/gradle/src
RUN gradle build --no-daemon

FROM openjdk:11.0
EXPOSE 8080
RUN mkdir /app
VOLUME /tmp
COPY --from=build /home/gradle/src/build/libs/*.jar /app/spring-boot-application.jar

#ENTRYPOINT ["java", "-XX:+UnlockExperimentalVMOptions", "-Djava.security.egd=file:/dev/./urandom","-jar","/app/spring-boot-application.jar"]

#ENTRYPOINT ["java",  "-XX:+UnlockExperimentalVMOptions", "-XX:+UseCGroupMemoryLimitForHeap", "-Djava.security.egd=file:/dev/./urandom","-jar","/app/spring-boot-application.jar"]
ENTRYPOINT ["sh", "-c", "java -jar /app/spring-boot-application.jar"]