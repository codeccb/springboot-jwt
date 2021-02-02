FROM openjdk:8-jre
EXPOSE 8080

WORKDIR /apps

ADD jwt-springboot-1.0.jar /apps

ENTRYPOINT ["java","-jar"]
CMD ["jwt-springboot-1.0.jar"]

