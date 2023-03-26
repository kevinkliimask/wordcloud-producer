FROM openjdk:17
VOLUME /tmp
EXPOSE 8080
ADD build/libs/wordcloud-producer-1.jar app.jar
ENTRYPOINT ["java","-jar","/app.jar"]