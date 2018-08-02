FROM frolvlad/alpine-oraclejdk8:slim
VOLUME /tmp
ADD dubbo-consumer-2.0.0.jar app.jar
RUN sh -c 'touch /app.jar'
ENV JAVA_OPTS=""
ENTRYPOINT ["java","-Djava.security.egd=file:/dev/./urandom","-jar","/app.jar"]